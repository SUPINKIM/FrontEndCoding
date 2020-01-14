import 'package:flutter/material.dart';

void main() => runApp(MaterialApp(home: MyApp()));

class MyApp extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      appBar: AppBar(
          title: new Text("First Page"),
        backgroundColor: Colors.orangeAccent,
      ),
      body: ListView(                             // 1. 리스트뷰 생성하고
        children: <Widget>[
          ListTile(                           // 2. 리스트 항목 추가하면 끝!
            leading: Icon(Icons.map),
            title: Text('지도'),
            onTap: () => _showDialog(context, '지도'),
          ),
          ListTile(
            leading: Icon(Icons.photo),
            title: Text('사진'),
            onTap: () => _showDialog(context, '사진'),
          ),
          ListTile(
            leading: Icon(Icons.phone),
            title: Text('전화'),
            //enabled: false,                             // 비활성
            onTap: () => _showDialog(context, '전화'),
          ),
        ],
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          // Add your onPressed code here!
          Navigator.push(
            context,
            MaterialPageRoute(builder:(context)=>TabbarRote()),  //화면 이동
          );
        },
        child: Icon(Icons.add),
        backgroundColor: Colors.amberAccent,
      ),
      bottomNavigationBar: BottomAppBar(
        shape: const CircularNotchedRectangle(),
        child: Container(height: 50.0,),
      ),
      floatingActionButtonLocation: FloatingActionButtonLocation.centerDocked,
    );
  }

  // API에 있는 showDialog 함수와 이름이 같아서 밑줄(_) 접두사(private 함수)
  void _showDialog(BuildContext context, String text) {
    // 경고창을 보여주는 가장 흔한 방법.
    showDialog(
        context: context,
        builder: (BuildContext ctx) {
          return AlertDialog(
            title: Text('선택 완료!'),
            content: Text('$text 항목을 선택했습니다.'),
            // 주석으로 막아놓은 actions 매개변수도 확인해 볼 것.
            actions: <Widget>[
                 FlatButton(child: Text('확인'), onPressed: () => Navigator.pop(context)), //navigator.pop() => 이전 화면으로 돌아가기
             ],
          );
        }
    );
  }
}

class TabbarRote extends StatelessWidget{
  @override
  Widget build(BuildContext context){
    return MaterialApp(
        home: DefaultTabController(
          //탭의 수 설정
          length:3,
          child: Scaffold(
            appBar: AppBar(
              title:Text('test tab'),
              leading: new IconButton(icon: new Icon(Icons.arrow_back_ios, color: Colors.white),
                  onPressed: () => Navigator.of(context).pop(),
              ),
              backgroundColor: Colors.amberAccent,
              bottom:TabBar(
                tabs: [
                  Tab(icon:Icon(Icons.directions_car)),
                  Tab(icon:Icon(Icons.directions_transit)),
                  Tab(icon:Icon(Icons.directions_bike)),
                ],
              ),
            ),
            // TabVarView 구현. 각 탭에 해당하는 컨텐트 구성
            body: TabBarView(
              children: [
                Icon(Icons.directions_car),
                Icon(Icons.directions_transit),
                Icon(Icons.directions_bike),
              ],
            ),
            bottomNavigationBar: BottomAppBar(
              shape: const CircularNotchedRectangle(),
              child: Container(height: 50.0,),
            ),
          ),
        ),
    );
  }
}
