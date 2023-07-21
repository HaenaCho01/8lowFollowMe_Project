package com.sparta.followfollowmeproject.postMedia.controller;

import com.sparta.followfollowmeproject.common.dto.ApiResponseDto;
import com.sparta.followfollowmeproject.common.security.UserDetailsImpl;
import com.sparta.followfollowmeproject.postMedia.dto.PostMediaResponseDto;
import com.sparta.followfollowmeproject.postMedia.service.PostMediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostMediaController {

	private final PostMediaService postMediaService;

	// 선택한 게시글의 미디어 조회
	@GetMapping("/posts/{postId}/media")
	public ResponseEntity<List<PostMediaResponseDto>> getMediaByPostId(@PathVariable Long postId) {
		List<PostMediaResponseDto> responseDtos = postMediaService.getMediaByPostId(postId);
		return ResponseEntity.ok().body(responseDtos);
	}

	// 선택한 게시글에 미디어 저장
	@PostMapping("/posts/{postId}/media")
	public ResponseEntity<PostMediaResponseDto> uploadMedia(@PathVariable Long postId, @RequestParam("media") MultipartFile multipartFile, @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
		PostMediaResponseDto postMediaResponseDto = postMediaService.uploadMedia(postId, multipartFile, userDetails.getUser());
		return ResponseEntity.ok().body(postMediaResponseDto);
	}

	// 선택한 미디어 수정
	@PutMapping("/posts/{postId}/media/{mediaId}")
	public ResponseEntity<PostMediaResponseDto> updateMedia(@PathVariable Long postId, @PathVariable Long mediaId, @RequestParam("media") MultipartFile multipartFile, @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
		PostMediaResponseDto postMediaResponseDto = postMediaService.updateMedia(postId, mediaId, multipartFile, userDetails.getUser());
		return ResponseEntity.ok().body(postMediaResponseDto);
	}

	// 선택한 미디어 삭제
	@DeleteMapping("/posts/{postId}/media/{mediaId}")
	public ResponseEntity<ApiResponseDto> deleteMedia(@PathVariable Long postId, @PathVariable Long mediaId, @AuthenticationPrincipal UserDetailsImpl userDetails) throws IOException {
		postMediaService.deleteMedia(postId, mediaId, userDetails.getUser());
		return ResponseEntity.ok().body(new ApiResponseDto("해당 미디어 삭제를 성공했습니다.", 200));
	}
}
