document.addEventListener('DOMContentLoaded', () => {
  const form = document.getElementById('registrationForm');

  form.addEventListener('submit', (e) => {
    // 例: 入力チェックでクライアント側バリデーションを実施
    const email = document.getElementById('email').value.trim();
    const password = document.getElementById('password').value.trim();
    const confirmPassword = document.getElementById('confirmPassword').value.trim();
    let isValid = true;

    // エラーメッセージの初期化
    document.querySelectorAll('.error-message').forEach((el) => (el.textContent = ''));

    // メールアドレスチェック
    if (!email) {
      document.getElementById('emailError').textContent = 'メールアドレスを入力してください。';
      isValid = false;
    }

    // パスワードチェック
    if (password.length < 8) {
      document.getElementById('passwordError').textContent = 'パスワードは8文字以上で入力してください。';
      isValid = false;
    }

    // 確認用パスワードチェック
    if (password !== confirmPassword) {
      document.getElementById('confirmPasswordError').textContent = 'パスワードが一致しません。';
      isValid = false;
    }

    if (!isValid) {
      e.preventDefault();
    }
  });
});
