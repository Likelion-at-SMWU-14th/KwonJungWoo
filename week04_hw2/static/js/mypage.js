function copyEmail() {
  const email = document.getElementById("emailText").innerText;

  navigator.clipboard
    .writeText(email)
    .then(() => {
      alert("이메일 복사 완료");
    })
    .catch(() => {
      alert("복사 실패");
    });
}
