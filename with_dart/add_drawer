import 'package:flutter/material.dart';
import 'package:bmnav/bmnav.dart' as bmnav;


//스크롤이 필요한 화면이라면 return SingleChildScrollView로 한 번 더 감싸기

class MyApp extends StatelessWidget {

  @override
  Widget build(BuildContext context) {
    final titles = [ 'bike', 'boat', 'bus', 'car', 'railway', 'run', 'subway', 'transit', 'walk' ];
    final icons = [ Icons.directions_bike, Icons.directions_boat, Icons.directions_bus, Icons.directions_car, Icons.directions_railway, Icons.directions_run, Icons.directions_subway, Icons.directions_transit, Icons.directions_walk ];
    int currentTab=0;

    return Scaffold(
      appBar: AppBar(
          title: new Text("First Page"),
        backgroundColor: Colors.orangeAccent,
      ),
      drawer: Drawer(
        child: ListView(
          // Important: Remove any padding from the ListView.
          padding: EdgeInsets.zero,
          children: <Widget>[
            Container(
              height: 100.0,
              child: DrawerHeader(
                  child: Text('Drawer Header'),
                  decoration: BoxDecoration(
                    color: Colors.amber,
                  ),
                  margin: EdgeInsets.all(0.0),
                  padding: EdgeInsets.all(0.0),
              ),
            ),
            ListTile(
              title: Text('Item 1'),
              onTap: () {
                // Update the state of the app.
                // ...
              },
            ),
            ListTile(
              title: Text('Item 2'),
              onTap: () {
                // Update the state of the app.
                // ...
              },
            ),
          ],
        ),
      ),
      body: ListView.builder(
        itemCount: titles.length,
        itemBuilder: (context, index) {
          return Card( // <-- Card widget
            child: ListTile(
              leading: Icon(icons[index]),
              title: Text(titles[index]),
            ),
          );
        },
        /*                           // 1. 리스트뷰 생성하고
        children: <Widget>[
          ListTile(                           // 2. 리스트 항목 추가하면 끝!
            leading: Icon(Icons.map),
            title: Text('지도'),
            subtitle: Text('현재 나의 위치 파악하기'),
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
        ],*/
      ),
      floatingActionButton: FloatingActionButton(
        onPressed: () {
          // Add your onPressed code here!
          Navigator.push(
            context,
            MaterialPageRoute(builder:(context)=>TabbarRote()),
          );
        },
        child: Icon(Icons.add),
        backgroundColor: Colors.amberAccent,
      ),
      bottomNavigationBar: bmnav.BottomNav(
        index: currentTab,
        onTap: (i) {
          currentTab = i;
          if(currentTab==3){Navigator.push(
            context,
            MaterialPageRoute(builder:(context)=>Setting()),
          );}
        },
        elevation: 10.0,
        labelStyle: bmnav.LabelStyle(showOnSelect: true),
        items: [
          bmnav.BottomNavItem(Icons.home, label: 'Home'),
          bmnav.BottomNavItem(Icons.fitness_center, label: 'Workouts'),
          bmnav.BottomNavItem(Icons.person, label: 'Account'),
          bmnav.BottomNavItem(Icons.view_headline, label: 'Settings'),
        ],
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

class Setting extends StatelessWidget{
  @override
  Widget build(BuildContext context) {
    // TODO: implement build
    return Scaffold(
      appBar: AppBar(
        title: Text('Setting'),
        leading: new IconButton(icon: new Icon(Icons.arrow_back_ios, color: Colors.white),
          onPressed: () => Navigator.of(context).pop(),
        ),
        backgroundColor: Colors.orangeAccent,
      ),
      body: Card(
        child: Column(
          crossAxisAlignment: CrossAxisAlignment.start,
          children: <Widget>[
            Card(
              child: ListTile(
                contentPadding: const EdgeInsets.all(30.0) ,
                leading:Icon(Icons.account_circle),
                title: Text('ABC1234'),
                subtitle: Text('가장 최근 활동 : 1시간 전'),
                trailing: Icon(Icons.arrow_forward_ios),
                ),
              ),
          ],
        ),
      ),
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
            body: TabBarView(
              children: [
                Tab(
                  icon: Icon(Icons.directions_car),
                  child: new ButtonBar(
                    mainAxisSize: MainAxisSize.min,
                    children: <Widget>[new OutlineButton(child: const Text('outline'),onPressed: null)],),),
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
