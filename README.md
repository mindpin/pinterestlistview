Android pinterestlistview
============================
瀑布流布局组件
仿Pinterest带下拉刷新、无限增量加载新内容的瀑布流组件，可以设置横屏、竖屏列数，可以设置左右缩进。

## 如何引用此组件：
安装
```
git clone https://github.com/mindpin/pinterestlistview.git
cd pinterestlistview
mvn clean install
```

在maven项目，pom.xml添加以下依赖引用：

```
<dependency>
  <groupId>com.mindpin.android.pinterestlistview</groupId>
  <artifactId>pinterestlistview</artifactId>
  <version>0.1.0</version>
  <type>apklib</type>
</dependency>
```

## 使用说明
### listener
瀑布流视图位于顶部时继续以手势向下拖拽，可以触发一个事件，通过 **set_on_refresh_listener** 可以监听这个事件，不设置这个 listener 时，关闭向下拖拽的手势操作
```
PinterestListView list_view = // xxx;
list_view.set_on_refresh_listener(new PinterestListView.OnRefreshListener() {
    @Override
    public void on_refresh() {
      // 这里可以异步获取新的内容到瀑布流顶部
      // 或者全部刷新瀑布流内容
      // 或者做其他事情都可以
      // 该方法运行在UI线程

      // 数据异步加载结束后需要运行这个方法才会把
      // 刷新提示视图隐藏掉
      list_view.on_refresh_complete();
    }
});
```

瀑布流视图滑动到底部时，可以触发加载更多事件，通过 **set_on_load_more_listener** 可以监听这个事件，不设置这个 listener 时，关闭加载更多事件
```
PinterestListView list_view = // xxx;
list_view.set_on_load_more_listener(new PinterestListView.OnLoadMoreListener() {
    @Override
    public void on_load_more() {
      // 这里可以异步获取新的内容到瀑布流底部
      // 或者做其他事情都可以
      // 该方法运行在UI线程

      // 数据异步加载结束后需要运行这个方法才会把
      // 刷新提示视图隐藏掉
      list_view.on_load_more_complete();
    }
});
```

**显示滚动栏**
显示滚动栏必须给PinterestListView设置```android:scrollbars="vertical"```属性

### 顶部文字更换
瀑布流视图位于顶部时继续以手势向下拖拽，顶部会显示出一个提示刷新的视图，当向下拖拽但是还没有拖拽到可以触发刷新事件的程度时，并且手指未松开时，显示的文字用 set_text_pull_to_refresh 方法设置

```
view.set_text_pull_to_refresh("下拉刷新");
```
当拖拽到可以触发刷新事件，并且手指未松开时，显示的文字用 set_text_release_to_refresh 方法设置

```
view.set_text_release_to_refresh("松开刷新");
```
当拖拽到可以触发刷新事件，并手指松开时，开始刷新，这时显示的文字用 set_text_refreshing 方法设置

```
view.set_text_refreshing("刷新中");
```


## 示例
```
<com.mindpin.android.pinterestlistview.PinterestListView
xmlns:waterfall="http://schemas.android.com/apk/res-auto"
android:id="@+id/list"
android:layout_width="match_parent"
android:layout_height="match_parent"
waterfall:plaColumnNumber="2"
waterfall:plaLandscapeColumnNumber="3"
android:scrollbars="vertical"
>
</com.mindpin.android.pinterestlistview.PinterestListView>
```
表示竖屏时候为2栏（默认为2）、横平时候为3栏（默认为2）、显示竖向滚动栏
