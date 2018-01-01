## 简介
HelloViewPager是Hello系列的子项目，学习知识点的同时，通过造轮子以提升编程能力。本项目实现了简单的带有自动轮播功能的广告栏，可使用默认设置完成快速应用，也可通过提供的接口，实现广告栏的自定义效果。

<img width="25%" height="25%" src="https://github.com/morening/HelloViewPager/blob/master/snapshot/intro.png" />


## 实现功能
1. 自动轮播的广告栏功能
2. 跟随页面变化的Indicator
3. 设置广告栏title（包括内容，字体大小，颜色和位置）
4. 设置Indicator（包括自定义View，大小，颜色和位置）
5. 设置默认页面（用于未加载数据或加载失败时）
6. 多级数据源功能，方便多场景应用
7. 为了方便快捷应用，均提供默认设置，`两行代码`便可拥有基本广告栏功能

## 主要接口
1. 自动轮播（包括启用开关，轮播间隔）
2. Indicator（包括自定义View，大小，颜色和位置）
3. 广告栏title（包括内容，字体大小，颜色和位置）
4. 默认页面
5. 多级数据源，按照添加顺序，数据源被逐一加载。可通过场景或业务需求判断是否返回数据。

## 如何快速使用
`两行代码`，完成快速应用
```Java
PromotionView promotionView = new PromotionView(this);
promotionView.show();
```
## 接口列表
### Indicator
* setIndicatorCustomView(List<T> customViews)  设置Indicator自定义View
* setIndicatorCustomViewPosition(int width, int height, int marginStart, int marginEnd)  设置Indicator自定义View的位置
* setIndicatorPosition(LayoutParams lp)  设置Indicator的位置
* getIndicatorPosition()  获取Indicator的位置
* setIndicatorColor(int selected, int unselected)  设置Indicator的颜色
* enableIndicatorColorBalance(boolean enable)  是否开启Indicator颜色平衡
  
### PromotionView
* setPromotionTitleSize(int size)  设置Promotion标题字体大小
* setPromotionTitleColor(int color)  设置Promotion标题字体颜色
* enablePromotionTitleColorBalance(boolean enable)  是否开启Promotion标题字体颜色颜色平衡
* setTitlePosition(LayoutParams lp)  设置Promotion标题位置
* getTitlePosition()  获取Promotion标题位置

### 默认页面
* setDefaultPageTitle(String title)  设置默认页面标题
* setDefaultPageTitleSize(int size)  设置默认页面标题字体大小
* setDefaultPageTitleColor(int color)  设置默认页面标题字体颜色
* setDefaultPageTitlePosition(LayoutParams lp)  设置默认页面标题位置
* setDefaultPageBgImage(Drawable bgDrawable)  设置默认页面背景图片

### 自动轮播
* enableAutoScroll(boolean enable)  是否启动自动轮播
* setAutoScrollInterval(long interval)  设置自动轮播时间间隔

### 数据源
* addDataRepo(IRepository repo)  添加数据源

## Todo List
1. 完成单元测试
2. 实现更多的轮播效果
3. 实现广告栏title/Indicator颜色与背景图片反差功能
4. 收集需求，丰富接口
5. 尽可能完善文档
