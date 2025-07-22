document.addEventListener("DOMContentLoaded", function () {
  const reviewList = document.querySelector(".reviewList");
  const reviewModal = new bootstrap.Modal(document.getElementById("reviewModal"));
  const reviewContent = document.getElementById("reviewContent");

  let isEdit = false;
  let editingReviewId = null;

  // 댓글 목록 불러오기
  function loadReviews() {
    fetch(`/board/reviews/${boardId}/all`)
      .then(res => res.json())
      .then(data => {
        reviewList.innerHTML = "";
        data.forEach(review => {
          const li = document.createElement("li");
          li.className = "list-group-item d-flex justify-content-between align-items-center";
          li.innerHTML = `
            <div>
              <strong>${review.memberName}</strong><br/>
              ${review.content}
            </div>
            <div>
              ${review.memberId === writerId ? `
              <button class="btn btn-sm btn-outline-warning me-1 edit-btn" data-id="${review.reviewId}" data-content="${review.content}">수정</button>
              <button class="btn btn-sm btn-outline-danger delete-btn" data-id="${review.reviewId}">삭제</button>` : ``}
            </div>`;
          reviewList.appendChild(li);
        });
      });
  }

  // 댓글 등록 버튼
  document.getElementById("addReviewBtn").addEventListener("click", () => {
    if (writerId === 0) {
      alert("로그인 후 댓글을 작성할 수 있습니다.");
      return;
    }

    isEdit = false;
    reviewContent.value = "";
    reviewModal.show();
  });

  // 저장 버튼
  document.getElementById("saveReviewBtn").addEventListener("click", () => {
    const content = reviewContent.value;
    if (!content) return;

    const payload = {
      content: content,
      memberId: writerId,
      memberName: writerName
    };

    const url = isEdit ?
      `/board/reviews/${boardId}/${editingReviewId}` :
      `/board/reviews/${boardId}`;

    const method = isEdit ? "PUT" : "POST";

    fetch(url, {
      method: method,
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(payload)
    }).then(() => {
      reviewModal.hide();
      loadReviews();
    });
  });

  // 수정 클릭
  reviewList.addEventListener("click", (e) => {
    if (e.target.classList.contains("edit-btn")) {
      isEdit = true;
      editingReviewId = e.target.dataset.id;
      reviewContent.value = e.target.dataset.content;
      reviewModal.show();
    }

    if (e.target.classList.contains("delete-btn")) {
      const id = e.target.dataset.id;
      if (confirm("정말 삭제하시겠습니까?")) {
        fetch(`/board/reviews/${boardId}/${id}`, {
          method: "DELETE"
        }).then(() => loadReviews());
      }
    }
  });

  // 초기 로드
  loadReviews();
});
