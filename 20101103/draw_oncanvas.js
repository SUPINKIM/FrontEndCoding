'use strict'
//오늘 공부는 "Class"를 만들고 여러 객체를 생성해보기
//get과 set에 대해서 더 이해해보기

const canvas = document.querySelector('canvas');
const ctx = canvas.getContext('2d');


// 캔버스의 크기를 window.innerWidth와 
//window.innerHeight로 정의해줘야 viewport의 크기와 캔버스의 크기가 동일해진다.
canvas.width = window.innerWidth;
canvas.height = window.innerHeight;


//사각형 객체 생성
class Rectangle{
    constructor(width,height,color){
        this.width = width;
        this.height = height;
        this.color = color;
    }

    get area() {  //너비 계산
        return this.width * this.height;
    }

    set area(value) {  //너비 setting
        this.area = value;
    }

    draw(x,y){
        ctx.beginPath();
        ctx.fillRect(x,y,this.width,this.height);
        ctx.fillStyle = this.color;
        ctx.fill();
        //ctx.closePath();
        //fill() 메소드 호출 시, 열린 도형은 자동으로 닫히게 되므로  closePath()메소드를 호출하지 않아도 됩니다. 이것은 stroke() 메소드에는 적용되지 않습니다.
    }
}

//컬러 배열 생성 후 랜덤 컬러 리턴
function getColor() {
    let colors = ["#28B4B4","#FFA6C5","#FFD232","#B750EA","#6496FF",
    "#9AB9FF","#82EB5A","#FF1493","#FFF064","#5ABEF5","#5ABEF5",
    "#FF96FF","#B9062F"];
    let index = Math.floor(Math.random() * colors.length);
    return colors[index];
}

//마우스 시작 위치 및 현재 위치 구하기
let mouse = {
    x : undefined,
    y : undefined
};

let maxRadius = 50;
let minRadius = 5;

addEventListener("mousemove",function(e){
    mouse.x = e.clientX;
    mouse.y = e.clientY;
},false);

/*
//현재 마우스 위치에서 클릭하면 도형 생성
addEventListener("click",function(event){
    mouse.x = event.clientX;  
    mouse.y = event.clientY;
    //console.log(mouse.x , mouse.y);
    let rec1 = new Rectangle(40, 40, getColor());
    rec1.draw(mouse.x,mouse.y); 
},false);
*/

//201105 + 201106

//원 객체 만들기 
//컬러는 위에 만든 getColor() 색 추가해서 재사용


class Circle{

    constructor(x,y,dx,dy,radius,color){
        this.x = x;
        this.y = y;
        this.dx = dx;
        this.dy = dy;
        this.radius = radius;
        this.color = color;
        
    }

    draw(){
        ctx.beginPath();
        ctx.arc(this.x,this.y,this.radius,0,Math.PI * 2,false);
        ctx.fillStyle = this.color;
        ctx.fill();
        //ctx.closePath();

        //this.update();
    }

    update(){
        //오류의 원인 : dx,dy를 update 안에 지역 변수로 줬기 때문에 +/- 치환이 되지 않음.
        //let dx = 1;
        //let dy = 1;
        
        if(this.x+this.radius > window.innerWidth || this.x-this.radius < 0) {
            this.dx = -this.dx;
        }
        if(this.y+this.radius > window.innerHeight || this.y-this.radius < 0) {
            this.dy = -this.dy;
        }

        this.x+=this.dx;
        this.y+=this.dy;
        //console.log("바뀐 x,y "+this.x,this.y);
        
        
        //console.log(Math.abs(this.x-mouse.x),Math.abs(this.y-mouse.y));

        //interactivity
        if(Math.abs(mouse.x - this.x) < 50 && Math.abs(mouse.y - this.y) < 50){
            if(this.radius < maxRadius){
                this.radius += 3;
            }
        }            
        else if (this.radius > minRadius){
            this.radius -= 3;
        }
        

        this.draw();
    }


}

let circles = [];
let circle;
function init(){
    console.log("window : "+window.innerWidth,window.innerHeight);
    console.log("no window : "+innerWidth, innerWidth);
    console.log("canvas :" +canvas.width, canvas.height);
    for(let i=0; i<100; i++){
        let radius = Math.floor(Math.random () * 10)+5;
        let new_x = Math.floor(Math.random() * (window.innerWidth- 3 * radius)) + radius ;
        let new_y = Math.floor(Math.random() * (window.innerHeight - 3 * radius) + radius);
        let dx = Math.random() - 0.5;
        let dy = Math.random() - 0.5;
        circle = new Circle(new_x, new_y, dx, dy, radius, getColor());
        console.log(circle);
        circles.push(circle);
    }
}

function animate(){
    requestAnimationFrame(animate);

    ctx.clearRect(0,0,window.innerWidth,window.innerWidth);

    for(let i=0; i<circles.length; i++){
        circles[i].update();
    }

}


init();
animate();