package shen.da.ye.swipefragmentdemo.zhihu.event;

/**
 * Created by YoKeyword on 16/6/5.
 */
public class TabSelectedEvent {
    public int position;

    /**
     * 主要是为了解决重新点击底部按钮回到按钮最初的第一个Fragment的，比如我这个项目的第一个按钮是ZhiHuFirstFrag
     * ment,然后点击这个按钮的时候如果当前ZhiHuFragment正在显示的Fragment是FirstHomeFragment就刷新这个frag
     * ment,如果不是就弹出所有的fragment然后显示FirstHomeFragment
     *
     * @param position
     */
    public TabSelectedEvent(int position) {
        this.position = position;
    }
}
