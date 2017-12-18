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

import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.style.ReplacementSpan;

/**
 * Created by pandac on 12/12/14.
 * Add a bracket (as specified in the constructor) to the quoted portion of CharSequence, during rendering.
 */
public class BracketSpan extends ReplacementSpan {

    private char openBracket,closeBracket;

    public BracketSpan(char openBracket, char closeBracket) {
        this.openBracket = openBracket;
        this.closeBracket = closeBracket;
    }

    @Override
    /*
    I don't know if the text rendering engine will do funny things like kerning, so that the total width
    of the text with bracket is equal to the width of the text plus the width of the two bracket characters
    individually.

    Hence we calculate the total width from scratch here.
     */
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        String bracketed = openBracket+text.subSequence(start, end).toString()+closeBracket;
        float[] widths = new float[bracketed.length()];
        paint.getTextWidths(bracketed, 0, bracketed.length(), widths);
        float total = 0;
        for(float f : widths) total+=f;
        return (int)total;
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        String bracketed = openBracket + text.subSequence(start, end).toString()+closeBracket;
        canvas.drawText(bracketed, x, y, paint);
    }
}
