// === Swiper 초기화 ===

// 🎉 이벤트 슬라이더
new Swiper(".eventSwiper", {
  slidesPerView: 2,
  spaceBetween: 20,
  navigation: {
    nextEl: ".event-next",
    prevEl: ".event-prev"
  },
  pagination: {
    el: ".event-pagination",
    clickable: true
  },
  breakpoints: {
    0: { slidesPerView: 1 },
    576: { slidesPerView: 1 },
    768: { slidesPerView: 1.5 },
    992: { slidesPerView: 2 }
  }
});

// 🔥 핫이슈 슬라이더
new Swiper(".hotSwiper", {
  slidesPerView: 4,
  spaceBetween: 20,
  navigation: {
    nextEl: ".hot-next",
    prevEl: ".hot-prev"
  },
  pagination: {
    el: ".hot-pagination",
    clickable: true
  },
  breakpoints: {
    0: { slidesPerView: 1 },
    576: { slidesPerView: 2 },
    768: { slidesPerView: 3 },
    992: { slidesPerView: 4 }
  }
});

// 🆕 신상품 슬라이더
new Swiper(".newSwiper", {
  slidesPerView: 4,
  spaceBetween: 20,
  navigation: {
    nextEl: ".new-next",
    prevEl: ".new-prev"
  },
  pagination: {
    el: ".new-pagination",
    clickable: true
  },
  breakpoints: {
    0: { slidesPerView: 1 },
    576: { slidesPerView: 2 },
    768: { slidesPerView: 3 },
    992: { slidesPerView: 4 }
  }
});

// === 향후 추가될 사용자 인터랙션 예시 ===
// document.querySelector("#someButton").addEventListener("click", function() {
//   alert("이벤트 준비 중입니다.");
// });
