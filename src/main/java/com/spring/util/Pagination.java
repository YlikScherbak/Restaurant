package com.spring.util;

public class Pagination {

    private int currentPage;
    private int resultsPerPage;
    private int nextPage = 0;
    private int previousPage = 0;
    private long pagesCount = 0;
    private boolean pagination;
    private long count;

    public Pagination(long count) {
        setCount(count);
    }

    public Pagination() {
    }

    private void init() {
        if (count <= resultsPerPage) {
            setPagination(false);
        } else {
            if (currentPage == 0) {
                // if currentPage is 0 this means no page was set so >
                setCurrentPage(1);
                setNextPage(2);
                setPreviousPage(-1);
            } else {
                // Next Page Set
                if (currentPage * resultsPerPage < count) {
                    setNextPage(currentPage + 1);
                } else {
                    //No next Page
                    setNextPage(-1);
                }
                // Previous Page Set
                if (currentPage > 1) {
                    setPreviousPage(currentPage - 1);
                } else {
                    //No Previous Page
                    setPreviousPage(-1);
                }
            }
            setPagesCount(((count - (count % resultsPerPage)) / resultsPerPage));
            setPagination(true);
        }
    }

    public int getCurrentPage() {
        init();
        return currentPage;
    }

    public void setCurrentPage(int page) {
        this.currentPage = page;
    }

    public int getResultsPerPage() {
        init();
        return resultsPerPage;
    }

    public void setResultsPerPage(int resultsPerPage) {
        this.resultsPerPage = resultsPerPage;
        init();
    }

    public int getNextPage() {
        init();
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }

    public int getPreviousPage() {
        init();
        return previousPage;
    }

    public void setPreviousPage(int previousPage) {
        this.previousPage = previousPage;
    }

    public long getPagesCount() {
        init();
        return pagesCount;
    }

    public void setPagesCount(long pagesCount) {
        this.pagesCount = pagesCount;
    }

    public boolean isPagination() {
        init();
        return pagination;
    }

    public void setPagination(boolean pagination) {
        this.pagination = pagination;
    }

    public long getCount() {
        init();
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

}
