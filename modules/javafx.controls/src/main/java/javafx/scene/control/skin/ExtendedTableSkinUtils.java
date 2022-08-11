// ******************************************************************
//
// ExtendedTableSkinUtils.java
// Copyright 2019 PSI AG. All rights reserved.
// PSI PROPRIETARY/CONFIDENTIAL. Use is subject to license terms
//
// ******************************************************************

package javafx.scene.control.skin;

import com.sun.javafx.scene.control.TableColumnBaseHelper;
import com.sun.javafx.scene.control.skin.Utils;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumnBase;
import javafx.scene.control.TableView;
import javafx.scene.control.TreeTableColumn;
import javafx.scene.control.TreeTableView;

/**
 * @author created: pkruszczynski on 09.08.2019 14:09
 * @author last change: $Author: $ on $Date: $
 * @version $Revision: $
 */
class ExtendedTableSkinUtils
{

    @SuppressWarnings( "unchecked" )
    public static void resizeColumnToFitHeader( TableViewSkinBase< ?, ?, ?, ?, ? > tableSkin,
        TableColumnBase< ?, ? > tc )
    {
        if( !tc.isResizable() )
        {
            return;
        }

        Object control = tableSkin.getSkinnable();
        if( control instanceof TableView )
        {
            resizeColumnToFitHeader( (TableView)control, (TableColumn)tc, tableSkin );
        }
        else if( control instanceof TreeTableView )
        {
            resizeColumnToFitHeader( (TreeTableView)control, (TreeTableColumn)tc, tableSkin );
        }
    }

    static < T, S > void resizeColumnToFitHeader( TableView< T > tv, TableColumn< T, S > tc,
        TableViewSkinBase tableSkin )
    {
        if( !tc.isResizable() )
        {
            return;
        }
        double maxWidth = getHeaderWidth( tc, tableSkin );

        if( tv.getColumnResizePolicy() == TableView.CONSTRAINED_RESIZE_POLICY && tv.getWidth() > 0 )
        {

            if( maxWidth > tc.getMaxWidth() )
            {
                maxWidth = tc.getMaxWidth();
            }

            int size = tc.getColumns().size();
            if( size > 0 )
            {
                resizeColumnToFitHeader( tableSkin, tc.getColumns().get( size - 1 ) );
                return;
            }

            TableSkinUtils.resizeColumn( tableSkin, tc, Math.round( maxWidth - tc.getWidth() ) );
        }
        else
        {
            TableColumnBaseHelper.setWidth( tc, maxWidth );
        }
    }

    static < T, S > void resizeColumnToFitHeader( TreeTableView< T > ttv, TreeTableColumn< T, S > tc,
        TableViewSkinBase tableSkin )
    {
        if( !tc.isResizable() )
        {
            return;
        }
        double maxWidth = getHeaderWidth( tc, tableSkin );

        if( ttv.getColumnResizePolicy() == TreeTableView.CONSTRAINED_RESIZE_POLICY && ttv.getWidth() > 0 )
        {

            if( maxWidth > tc.getMaxWidth() )
            {
                maxWidth = tc.getMaxWidth();
            }

            int size = tc.getColumns().size();
            if( size > 0 )
            {
                resizeColumnToFitHeader( tableSkin, tc.getColumns().get( size - 1 ) );
                return;
            }

            TableSkinUtils.resizeColumn( tableSkin, tc, Math.round( maxWidth - tc.getWidth() ) );
        }
        else
        {
            TableColumnBaseHelper.setWidth( tc, maxWidth );
        }
    }


    public static < T, S > double getHeaderWidth( final TableColumn< T, S > tc,
        final TableViewSkinBase tableSkin ) {
        return TableSkinUtils.getHeaderWidth( tc, tableSkin );
    }

    public static < T, S > double getContentWidth( final TableView< T > tv, final TableColumn< T, S > tc,
        final TableViewSkinBase tableSkin, final int maxRows ) {
        return TableSkinUtils.getContentWidth( tv, tc, tableSkin, maxRows );
    }


    static < T, S > double getHeaderWidth( final TreeTableColumn< T, S > tc,
        final TableViewSkinBase tableSkin ) {
        return TableSkinUtils.getHeaderWidth( tc, tableSkin );
    }

    static < T, S > Double getContentWidth( final TreeTableView< T > ttv, final TreeTableColumn< T, S > tc,
        final TableViewSkinBase tableSkin, final int maxRows ) {
        return TableSkinUtils.getContentWidth( ttv, tc, tableSkin, maxRows );
    }
}
