// レシピ評価機能
function rateRecipe(rating, container) {
    const recipeId = container.getAttribute('data-recipe-id');
    const stars = container.querySelectorAll('.star');
    const ratingMessage = document.querySelector('.rating-message');
    
    // 星の見た目を更新
    stars.forEach((star, index) => {
        const starIcon = star.querySelector('i');
        if (index < rating) {
            starIcon.className = 'fas fa-star'; // 塗りつぶし
        } else {
            starIcon.className = 'far fa-star'; // 輪郭のみ
        }
    });
    
    // バックエンドに評価を送信
    fetch(`/recipe/api/recipe/${recipeId}/rate`, {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify({ rating: rating })
    })
    .then(response => {
        if (!response.ok) {
            throw new Error('評価に失敗しました');
        }
        return response.json();
    })
    .then(data => {
        // 成功メッセージを表示
        ratingMessage.textContent = `${rating}点の評価をありがとうございます！`;
        ratingMessage.style.color = '#4c6ef5';
        
        // 平均評価を更新（オプション - APIがこの情報を返す場合）
        if (data.averageRating) {
            document.querySelector('.rating').textContent = `★ ${data.averageRating}`;
            document.querySelector('.rating-count').textContent = 
                `(${data.ratingCount}件の評価)`;
        }
    })
    .catch(error => {
        console.error('評価エラー:', error);
        ratingMessage.textContent = '評価に失敗しました。もう一度お試しください。';
        ratingMessage.style.color = '#dc3545';
    });
}

// お気に入り切り替え機能
function toggleFavorite(button) {
    event.stopPropagation(); // カード全体のクリックイベントを防止
    const recipeId = button.getAttribute('data-recipe-id');
    
    // 処理中の状態を視覚的に示す（オプション）
    button.classList.add('processing');
    
    // バックエンドへのリクエスト
    fetch('/recipe/api/recipe/favorites', {
        method: 'PUT',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({
            recipeId: recipeId
        })
    })
    .then(response => response.json())
    .then(data => {
        // 処理中表示を解除
        button.classList.remove('processing');
        
        if (data.success) {
            // 成功した場合のみボタンの状態を切り替え
            button.classList.toggle('active');
        } else {
            // エラーメッセージを表示（オプション）
            console.error('お気に入り操作に失敗しました');
        }
    })
    .catch(error => {
        // 処理中表示を解除
        button.classList.remove('processing');
        console.error('Error:', error);
    });
}

// ページ読み込み時に、ユーザーが既に評価している場合は星を表示
document.addEventListener('DOMContentLoaded', function() {
    const userRating = document.querySelector('.star-container').getAttribute('data-user-rating');
    if (userRating) {
        const stars = document.querySelectorAll('.star');
        stars.forEach((star, index) => {
            const starIcon = star.querySelector('i');
            if (index < userRating) {
                starIcon.className = 'fas fa-star'; // 塗りつぶし
            }
        });
        document.querySelector('.rating-message').textContent = 'あなたの評価: ' + userRating + '点';
    }
});