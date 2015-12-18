package fr.cmm.helper;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

public class Pagination {
    // 1 based page index
    private int pageIndex;

    private int pageSize;

    private long count;

    public static final int PAGINATION_SIZE = 10;


    public int getPreviousPageIndex() {
        return isFirstPage() ? pageIndex : pageIndex - 1;
    }

    public int getNextPageIndex() {
        return isLastPage() ? pageIndex : pageIndex + 1;
    }

    public boolean isFirstPage() {
        return pageIndex == 1;
    }

    public boolean isLastPage() {
        return pageIndex * pageSize >= count;
    }

    public int getPageCount() {
        if (count < pageSize ) {return 1;}
        else if (count % pageSize == 0 ) { return (int) (count / pageSize); }
        else { return (int) ((count / pageSize) +1); }
    }

    public List<Integer> getPages() {
        List pageList = new ArrayList<>();
        int i = 1;
        if (getPageCount() < PAGINATION_SIZE) {
            while (i<getPageCount()+1MVC-5 : getPages() dans Pagination) {
                pageList.add(i);
                i = i + 1 ;
            }
        }
        else {
            while (i<PAGINATION_SIZE+1) {
                pageList.add(getPageCount()-5+i);
                i = i + 1 ;
            }
        }
        return pageList;
    }

    public int getPageIndex() { return pageIndex; }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }
}
