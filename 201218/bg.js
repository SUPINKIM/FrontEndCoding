'use strict';

function successLocation(position) {
  console.log('success!');
  console.log(position.coords);
}

function failLocation(position) {
  console.log('위치 정보를 찾을 수 없습니다.');
}
function getLocation() {
  navigator.geolocation.getCurrentPosition(successLocation, failLocation);
}

function loadImage() {
  const index = makeRandom();
  const body = document.querySelector('body');
  const img = new Image();
  img.src = `/images/${index + 1}.jpg`;
  body.appendChild(img);
  img.classList.add('images');
}

function makeRandom() {
  const random = Math.floor(Math.random() * 3);
  return random;
}

function init() {
  setInterval(loadImage, 1000);
  //getLocation();
}

init();
