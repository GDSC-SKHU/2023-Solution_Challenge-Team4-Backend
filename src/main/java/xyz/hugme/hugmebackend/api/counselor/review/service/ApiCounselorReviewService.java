package xyz.hugme.hugmebackend.api.counselor.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import xyz.hugme.hugmebackend.api.common.SingleRspsTemplate;
import xyz.hugme.hugmebackend.api.counselor.review.dto.ReviewDto;
import xyz.hugme.hugmebackend.domain.user.client.Client;
import xyz.hugme.hugmebackend.domain.user.client.ClientService;
import xyz.hugme.hugmebackend.domain.user.counselor.Counselor;
import xyz.hugme.hugmebackend.domain.user.counselor.CounselorService;
import xyz.hugme.hugmebackend.domain.user.counselor.review.CounselorReview;
import xyz.hugme.hugmebackend.domain.user.counselor.review.CounselorReviewService;

@RequiredArgsConstructor
@Transactional(readOnly = true)
@Service
public class ApiCounselorReviewService {

    private final CounselorReviewService counselorReviewService;
    private final CounselorService counselorService;
    private final ClientService clientService;

    @Transactional
    public SingleRspsTemplate<ReviewDto.Response> saveReview(Long counselorId, Client client, ReviewDto.Request reviewRequestDto) {
        // CounselorReview Casecade Persist => Client, Counselor
        Counselor counselor = counselorService.findById(counselorId);
        Client persistedClient = clientService.save(client);// save detached client

        CounselorReview counselorReview = reviewRequestDto.toEntity(counselor, persistedClient);
        counselorReviewService.save(counselorReview);

        ReviewDto.Response response = new ReviewDto.Response(counselorReview.getId(), counselorReview.getRate(), counselorReview.getContent());
        return new SingleRspsTemplate<>(HttpStatus.CREATED.value(), response);
    }

    @Transactional
    public SingleRspsTemplate<ReviewDto.Response> editReview(Long reviewId, Client client, ReviewDto.Request reviewRequestDto) {
        CounselorReview counselorReview = counselorReviewService.findById(reviewId);
        counselorReview.validateUpdateReview(client.getId());
        counselorReview.updateReview(reviewRequestDto.getRate(), reviewRequestDto.getContent());

        ReviewDto.Response response = new ReviewDto.Response(counselorReview.getId(), counselorReview.getRate(), counselorReview.getContent());
        return new SingleRspsTemplate<>(HttpStatus.OK.value(), response);
    }

    @Transactional
    public void deleteReview(Long reviewId, Client client) {
        CounselorReview counselorReview = counselorReviewService.findById(reviewId);
        counselorReview.validateUpdateReview(client.getId());
        counselorReviewService.deleteById(reviewId);
    }
}
