'use strict';

//class

class Person {
  //constructor (매개변수 받아서 초기화 시키는 역할)
  constructor(name, age) {
    this.name = name;
    this.age = age;
  }
  get age() {
    return this._age;
  }

  set age(value) {
    /*if (value < 0) {
      this._age = 0;
    } else {
      this._age = value;
    }*/
    value < 0 ? (this._age = 0) : (this._age = value);
  }

  //method
  sayHellow() {
    console.log(`Hi, ${this.name}`);
  }
}

//constructor function
/*function Shape(width, height) {
  return {
    width,
    height,
  };
}*/

class Shape {
  constructor(width, height) {
    this.width = width;
    this.height = height;
  }

  get width() {
    return this._width;
  }
  set width(value) {
    value < 0 ? (this._width = 0) : (this._width = value);
  }
  get height() {
    return this._height;
  }
  set height(value) {
    value < 0 ? (this._height = 0) : (this._height = value);
  }

  getArea() {
    return this.width * this.height;
  }
}

class TryAngle extends Shape {
  getArea() {
    return (this.width * this.height) / 2;
  }
}

function init() {
  const supin = new Person('supin', 24);
  supin.sayHellow();

  const lee = new Person('lee', -21);
  console.log(lee.age);

  const rectangle = new Shape(20, 30);
  console.log(rectangle.getArea());

  const tryangle = new TryAngle(20, 30);
  console.log(tryangle.getArea());
}

init();
