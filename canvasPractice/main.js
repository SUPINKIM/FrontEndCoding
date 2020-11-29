var canvas = document.querySelector('canvas');

canvas.width = window.innerWidth;
canvas.height = window.innerHeight;

var c = canvas.getContext('2d');
//c.fillStyle = "#6AC793";
//c.fillRect(100, 100, 100, 100);
//c.fillStyle = "#FFC5D0";
//c.fillRect(300, 500, 100, 100);
//c.fillStyle = "#FF50CF";
//c.fillRect(600, 100, 100, 100);

console.log(canvas);

//line
//c.moveTo(50,300);
//c.beginPath();
//c.lineTo(300, 100);
//c.lineTo(400,300);
//c.strokeStyle = "#fa34a3";
//c.stroke();

// arc/Circle

//c.beginPath();
//c.arc(300, 300, 30, Math.PI*2, false);
//c.strokeStyle="blue";
//c.stroke();

//for (let i = 0 ; i<20; i++) {
//    c.beginPath();
//    //c.arc(i*40+160, i*50+200, 30, Math.PI*2, false);
//    let x = Math.random() * window.innerWidth;
//    let y = Math.random() * window.innerHeight;
//    c.arc(x, y, 25,Math.PI*2, false);
//    c.strokeStyle="#E16A9D";
//    c.stroke();   
//}



let x = 300;
let y = 300;
let dx = 2;
function animate(){
    requestAnimationFrame(animate);
    //let x = Math.random() * window.innerWidth;
    //let y = Math.random() * window.innerHeight;
    c.clearRect(0,0,window.innerWidth,window.innerHeight);

    c.beginPath();
    c.arc(x, y, 30, Math.PI*2, false);
    c.strokeStyle="blue";
    c.stroke();

    if((x+30) > window.innerWidth || (y+30) > window.innerHeight){
        dx = -dx; //view 너비/높이보다 x/y값이 커지면 반대로 이동
    }
    if((x-30) < 0 || (y-30) < 0){
        dx = -dx;  //x,y 좌표가 (0,0)보다 작아지면 반대로 이동
    }
    x+=dx; //1씩 증가 
    y+=dx;

}

animate();
