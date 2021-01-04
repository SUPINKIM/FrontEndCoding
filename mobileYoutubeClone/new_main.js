'use strict';

const like = document.querySelector('#like');
const morebnt = document.querySelector('#moreBnt');
const title = document.querySelector('.title');

let onClicked = false;
let onClickedM = false;

function changeColor(event) {
  if (!onClicked) {
    onClicked = true;
    event.target.parentNode.style.color = '#2e64fe';
  } else {
    onClicked = false;
    event.target.parentNode.style.color = '#2e2e2e';
  }
}

function moreText(event) {
  if (!onClickedM) {
    onClickedM = true;
    morebnt.classList.remove('fa-sort-down');
    morebnt.classList.add('fa-sort-up');
    title.classList.remove('clamp');
  } else {
    onClickedM = false;
    morebnt.classList.remove('fa-sort-up');
    morebnt.classList.add('fa-sort-down');
    title.classList.add('clamp');
  }
}

like.addEventListener('click', changeColor, false);
morebnt.addEventListener('click', moreText, false);
