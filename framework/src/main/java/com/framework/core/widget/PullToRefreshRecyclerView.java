//package com.framework.core.widget;
//
//import android.content.Context;
//import android.support.v7.widget.GridLayoutManager;
//import android.support.v7.widget.LinearLayoutManager;
//import android.support.v7.widget.RecyclerView;
//import android.support.v7.widget.StaggeredGridLayoutManager;
//import android.util.AttributeSet;
//import android.view.View;
//
///**
// * 作者：Created by Laulee
// * 时间：2018/4/18.
// */
//
//public class PullToRefreshRecyclerView extends PullToRefreshBase<RecyclerView> {
//
//    private boolean isToPullToRefresh = true;
//    private RecyclerView refreshView;
//    private int minScrollY = 0;
//    private boolean doRefresh;
//
//    public PullToRefreshRecyclerView(Context context) {
//        super(context);
//    }
//
//    public PullToRefreshRecyclerView(Context context, AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    public PullToRefreshRecyclerView(Context context, Mode mode) {
//        super(context, mode);
//    }
//
//    public PullToRefreshRecyclerView(Context context, Mode mode, AnimationStyle animStyle) {
//        super(context, mode, animStyle);
//    }
//
//    @Override
//    public Orientation getPullToRefreshScrollDirection() {
//        return Orientation.VERTICAL;
//    }
//
//    @Override
//    protected RecyclerView createRefreshableView(Context context, AttributeSet attrs) {
//        refreshView = new RecyclerView(context, attrs);
//        refreshView.setId(android.R.id.list);
//        return refreshView;
//    }
//
//    @Override
//    protected boolean isReadyForPullEnd() {
//        if (!isToPullToRefresh) {
//            return false;
//        }
//        //        int lastPosition = -1;
//        //
//        //        RecyclerView.LayoutManager layoutManager = refreshView.getLayoutManager( );
//        //        if( layoutManager instanceof GridLayoutManager ) {
//        //            //通过LayoutManager找到当前显示的最后的item的position
//        //            lastPosition = ( (GridLayoutManager) layoutManager )
//        // .findLastVisibleItemPosition( );
//        //        } else if( layoutManager instanceof LinearLayoutManager ) {
//        //            lastPosition = ( (LinearLayoutManager) layoutManager )
//        // .findLastVisibleItemPosition( );
//        //        } else if( layoutManager instanceof StaggeredGridLayoutManager ) {
//        //            //因为StaggeredGridLayoutManager的特殊性可能导致最后显示的item存在多个，所以这里取到的是一个数组
//        //            //得到这个数组后再取到数组中position值最大的那个就是最后显示的position值了
//        //            int[] lastPositions = new int[( (StaggeredGridLayoutManager) layoutManager )
//        //                    .getSpanCount( )];
//        //            ( (StaggeredGridLayoutManager) layoutManager )
//        //                    .findLastVisibleItemPositions( lastPositions );
//        //            lastPosition = findMax( lastPositions );
//        //        }
//        //
//        //        //时判断界面显示的最后item的position是否等于itemCount总数-1也就是最后一个item的position
//        //        //如果相等则说明已经滑动到最后了
//        //        if( lastPosition == refreshView.getLayoutManager( ).getItemCount( ) - 1 ) {
//        //            refreshView.smoothScrollToPosition( refreshView.getLayoutManager( )
//        // .getItemCount( ) - 1 );
//        //            return true;
//        //        }
//        //        return false;
//
//        //上面的代码也有一个bug，当最后一个item刚显示出来的时候停止滑动这个时候也会触发滑动到底部的操作
//        //得到当前显示的最后一个item的view
//        View lastChildView = refreshView.getLayoutManager()
//                .getChildAt(refreshView.getLayoutManager().getChildCount() - 1);
//
//        if (lastChildView != null) {
//
//            //得到lastChildView的bottom坐标值
//            int lastChildBottom = lastChildView.getBottom();
//            //得到Recyclerview的底部坐标减去底部padding值，也就是显示内容最底部的坐标
//            int recyclerBottom = refreshView.getBottom() - refreshView.getPaddingBottom();
//            //通过这个lastChildView得到这个view当前的position值
//            int lastPosition = refreshView.getLayoutManager().getPosition(lastChildView);
//
//            //判断lastChildView的bottom值跟recyclerBottom
//            //判断lastPosition是不是最后一个position
//            //如果两个条件都满足则说明是真正的滑动到了底部
//            if ((lastChildBottom <= recyclerBottom && lastPosition == refreshView
//                    .getLayoutManager().getItemCount() - 1)) {
//                return true;
//            } else
//                return false;
//        }
//
//        return false;
//    }
//
//    @Override
//    protected boolean isReadyForPullStart() {
//        int firstPosition = -1;
//
//        RecyclerView.LayoutManager layoutManager = refreshView.getLayoutManager( );
//        if( layoutManager instanceof GridLayoutManager) {
//            //通过LayoutManager找到当前显示的最后的item的position
//            firstPosition = ( (GridLayoutManager) layoutManager )
//                    .findFirstCompletelyVisibleItemPosition( );
//        } else if( layoutManager instanceof LinearLayoutManager) {
//            firstPosition = ( (LinearLayoutManager) layoutManager )
//                    .findFirstCompletelyVisibleItemPosition( );
//        } else if( layoutManager instanceof StaggeredGridLayoutManager) {
//            //因为StaggeredGridLayoutManager的特殊性可能导致最后显示的item存在多个，所以这里取到的是一个数组
//            //得到这个数组后再取到数组中position值最大的那个就是最后显示的position值了
//            int[] lastPositions = new int[( (StaggeredGridLayoutManager) layoutManager )
//                    .getSpanCount( )];
//            ( (StaggeredGridLayoutManager) layoutManager )
//                    .findLastVisibleItemPositions( lastPositions );
//            firstPosition = findMin( lastPositions );
//        }
//
//        //时判断界面显示的最后item的position是否等于itemCount总数-1也就是最后一个item的position
//        //如果相等则说明已经滑动到最后了
//
//        if(firstPosition==0){
//            return true;
//        }
//        View fastChildView = refreshView.getLayoutManager( ).getChildAt( 0 );
//        if( fastChildView != null ) {
//
//            //得到fastChildView的top坐标值
//            int fastChildTop = fastChildView.getTop( );
//            //得到Recyclerview的底部坐标减去底部padding值，也就是显示内容最底部的坐标
//            int recyclerTop = refreshView.getTop( ) - refreshView.getPaddingTop( );
//            //通过这个lastChildView得到这个view当前的position值
//
//            if( fastChildTop >= recyclerTop ) {
//                return true;
//            } else
//                return false;
//        }
//        return false;
//    }
//
//    //找到数组中的最大值
//    private int findMax( int[] lastPositions ) {
//        int max = lastPositions[0];
//        for( int value : lastPositions ) {
//            max = value > max ? value : max;
//        }
//        return max;
//    }
//
//    //找到数组的最小值
//    private int findMin( int[] lastPositions ) {
//        int min = lastPositions[0];
//        for( int value : lastPositions ) {
//            min = value > min ? min : value;
//        }
//        return min;
//    }
//
//    public void isToPullRefresh(boolean isToPullToRefresh) {
//        this.isToPullToRefresh = isToPullToRefresh;
//    }
//
//    public void autoRefresh() {
//        doRefresh = true;
//        refreshView.scrollTo(0, 0);
//        setRefreshing(doRefresh);
//    }
//
//    @Override
//    protected void onRefreshing(boolean doScroll) {
//        super.onRefreshing(doScroll);
//        if (isReadyForPullStart()) {
//            refreshView.scrollToPosition(0);
//        } else if (isReadyForPullEnd()) {
//            refreshView
//                    .smoothScrollToPosition(refreshView.getLayoutManager().getItemCount() - 1);
//        }
//        doRefresh = false;
//    }
//
//    /**
//     * 获取刷新界面
//     *
//     * @return
//     */
//    public RecyclerView getRefreshView() {
//        return refreshView;
//    }
//}
