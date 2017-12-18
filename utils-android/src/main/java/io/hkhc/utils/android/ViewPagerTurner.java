/*
 * Copyright (c) 2017. Herman Cheung
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package io.hkhc.utils.android;

import android.animation.ValueAnimator;
import android.support.v4.view.ViewPager;

import io.hkhc.utils.Consts;


/**
 * Created by herman on 6/7/15.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public class ViewPagerTurner {

    private static final String TAG = Consts.logTag("VPT");

    private static final int DEFAULT_DURATION = 400;

    private float lastAnimatedValue = 0;
    private ViewPager viewPager = null;
    private int duration ;
    private ValueAnimator animator = null;

    public ViewPagerTurner(ViewPager viewPager, int duration) {
        this.viewPager = viewPager;
        this.duration = duration;
    }

    public ViewPagerTurner(ViewPager viewPager) {
        this(viewPager, DEFAULT_DURATION);
    }

    public void viewLeft() {
        move(1);
    }

    public void viewRight() {
        move(-1);
    }

    private void move(int dir) {
//        System.out.println("move current:"+viewPager.getCurrentItem() + " total:"+viewPager.getAdapter().getCount()+" dir:"+dir);
        viewPager.setCurrentItem(viewPager.getCurrentItem()-dir,true);
//        System.out.println("move after :" + viewPager.getCurrentItem());
    }

//    private void move(int dir) {
//
//        if (animator!=null && animator.isRunning()) {
//            Log.d(TAG, "animating, end it now");
//            animator.end();
//        }
//        if (viewPager.isFakeDragging()) {
//            Log.d(TAG, "dragging");
//            return;
//        }
//
//        lastAnimatedValue = 0;
//        Log.d(TAG, "before ofFloat viewPager width = " + viewPager.getMeasuredWidth());
//        animator = ValueAnimator.ofFloat(lastAnimatedValue, dir*viewPager.getMeasuredWidth());
//        Log.d(TAG, "after ofFloat");
//        animator.setDuration(duration);
//        animator.addUpdateListener(a -> {
//            float newValue = ((Float) a.getAnimatedValue()).floatValue();
//            float delta = newValue - lastAnimatedValue;
//            System.out.println("delta = " + delta);
//            lastAnimatedValue = newValue;
//            if (delta>0)
//                viewPager.fakeDragBy(delta);
//        });
//        animator.addListener(new Animator.AnimatorListener() {
//            @Override
//            public void onAnimationStart(Animator animator) {
//                Log.d(TAG, "onAnimationStart");
//            }
//
//            @Override
//            public void onAnimationEnd(Animator animator) {
////                viewPager.endFakeDrag();
//                viewPager.endFakeDrag();
//            }
//
//            @Override
//            public void onAnimationCancel(Animator animator) {
////                viewPager.endFakeDrag();
//                viewPager.endFakeDrag();
//            }
//
//            @Override
//            public void onAnimationRepeat(Animator animator) {
//
//            }
//        });
//        viewPager.beginFakeDrag();
//        animator.start();
//
//        Log.d(TAG, "animator started");
//
//    }

}
