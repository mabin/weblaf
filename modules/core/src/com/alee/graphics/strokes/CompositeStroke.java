/*
 * This file is part of WebLookAndFeel library.
 *
 * WebLookAndFeel library is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * WebLookAndFeel library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with WebLookAndFeel library.  If not, see <http://www.gnu.org/licenses/>.
 */

package com.alee.graphics.strokes;

import java.awt.*;

/**
 * User: mgarin Date: 13.06.11 Time: 15:46
 */

public class CompositeStroke implements Stroke
{
    private Stroke stroke1;
    private Stroke stroke2;

    public CompositeStroke ( Stroke stroke1, Stroke stroke2 )
    {
        this.stroke1 = stroke1;
        this.stroke2 = stroke2;
    }

    @Override
    public Shape createStrokedShape ( Shape shape )
    {
        return stroke2.createStrokedShape ( stroke1.createStrokedShape ( shape ) );
    }
}
