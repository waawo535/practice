
  const recipeImageInput = document.getElementById('recipeImageInput');
  const imagePreview = document.getElementById('imagePreview');

  recipeImageInput.addEventListener('change', function (e) {
    const file = e.target.files[0];
    if (!file) {
      imagePreview.innerHTML = '';
      return;
    }
    const reader = new FileReader();
    reader.onload = function () {
      const img = document.createElement('img');
      img.src = reader.result;
	  img.style.maxWidth = '50%';
      imagePreview.innerHTML = '';
      imagePreview.appendChild(img);
    };
    reader.readAsDataURL(file);
  });



// ********** 材料の動的追加 **********
const ingredientList = document.getElementById('ingredientList');
const addIngredientBtn = document.getElementById('addIngredientBtn');

function addIngredientItem() {
  const ingredientList = document.getElementById('ingredientList');

  // 既存の要素をコピー
  const firstItem = ingredientList.querySelector('.ingredient-item');
  const newItem = firstItem.cloneNode(true);

  // インデックスを更新
  const index = document.querySelectorAll('.ingredient-item').length;
  const input = newItem.querySelector('input');
  input.name = `recipeIngredients[${index}].ingredientName`;
  input.value = ''; // 新しい行なので値をクリア

  // 新しい要素を追加
  ingredientList.appendChild(newItem);
}

//// 初期表示として１つ作る
//addIngredientItem();

// プラスボタンが押されたら材料を１行追加
addIngredientBtn.addEventListener('click', () => {
  addIngredientItem();
});

// ********** 作り方の動的追加 **********
const stepsList = document.getElementById('stepsList');
const addStepBtn = document.getElementById('addStepBtn');

let stepCount = 0;

function addStepItem() {
	const stepsList = document.getElementById('stepsList');
	
	// 既存の要素をコピー
	const firstItem = stepsList.querySelector('.stepsList-item');
	const newItem = firstItem.cloneNode(true);
	
	// インデックスを更新
	const index = document.querySelectorAll('.stepsList-item').length;
	const input = newItem.querySelector('input');
	input.name = `stepsList[${index}]`;
	input.value = ''; // 新しい行なので値をクリア
	
	// 新しい要素を追加
	stepsList.appendChild(newItem);
}

// 初期表示として１ステップ作る
//addStepItem();

// プラスボタンが押されたらステップを１つ追加
addStepBtn.addEventListener('click', () => {
  addStepItem();
});

// ********** フォーム送信・キャンセル **********
const recipeForm = document.getElementById('recipeForm');


function handleClear() {
  if (confirm('入力を取り消してよろしいですか？')) {
    // キャンセル時のリセット処理など
    recipeForm.reset();
    imagePreview.innerHTML = '';
    ingredientList.innerHTML = '';
    stepsList.innerHTML = '';
    stepCount = 0;
    addIngredientItem();
    addStepItem();
  }
}
