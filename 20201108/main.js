const canvas = document.querySelector('canvas');
const ctx = canvas.getContext('2d');

canvas.width = window.innerWidth;
canvas.height = window.innerHeight;

class Circle{
    constructor(x,y,radius,color){
        this.x = x;
        this.y = y;
        this.radius = radius;
        this.color = color;
    }

    draw(){
        ctx.beginPath();
        ctx.fillStyle = this.color;
        ctx.arc(this.x, this.y, this.radius, 0, Math.PI * 2, false);
        ctx.fill();
        ctx.closePath();
    }
}

let circle1;
let circle2;
let circle3;
function init(){
    circle1 = new Circle(700,600,100,"#FEBEBE");
    circle2 = new Circle(600,600,100,"#E6749D");
    circle3 = new Circle(500,600,100,"#D28484");

}

function animate(){
    circle1.draw();
    circle2.draw();
    circle3.draw();

    ctx.beginPath();
    ctx.moveTo(30,90);
    ctx.lineTo(60,40);
    ctx.lineTo(90,90);
    ctx.lineTo(30,90);
    //ctx.stroke();
    ctx.fill();
}

init();
animate();