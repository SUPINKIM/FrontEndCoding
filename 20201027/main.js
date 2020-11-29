// 함수 선언
// 함수 호출

// 함수 선언
function doSomething() {
    console.log('hello');
}

function doSomething2(add){
    console.log(add);
}

function add(a, b) {
    let sum = a + b;
    console.log(sum);
    return sum;
}


// 변수에 함수 할당
let su = doSomething;
su();

//함수 호출
doSomething();
console.log("리턴 받아 온 값은 " + add(4,5));
//함수 이름이 같아도 매개변수가 다르면 다른 함수,,가 아닌가 봄
doSomething2(add);

//su라는 변수가 doSomething 함수 전체를 받아버림
console.log(su);
