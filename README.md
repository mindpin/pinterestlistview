Android pinterestlistview
============================
瀑布流布局组件
仿Pinterest带下拉刷新、无限增量加载新内容的瀑布流组件，可以设置横屏、竖屏列数，可以设置左右缩进。

###如何引用此组件：
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

### 使用说明
下拉刷新需要**setOnRefreshListener**才会出现
```
mAdapterView.onRefreshComplete();
```
可隐藏刷新提示视图



增量加载需要**setOnLoadMoreListener**才会出现

```
mAdapterView.onLoadMoreComplete();
```
可隐藏读取更多提示视图

