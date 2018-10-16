package cl.jouer_club.coach_jouerclub.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Pagination {

    @SerializedName("total")
    @Expose
    private int total;
    @SerializedName("count")
    @Expose
    private int count;
    @SerializedName("per_page")
    @Expose
    private int perPage;
    @SerializedName("current_page")
    @Expose
    private int currentPage;
    @SerializedName("total_pages")
    @Expose
    private int totalPages;
    @SerializedName("links")
    @Expose
    private List<String> links = null;

    public Pagination() {
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPerPage() {
        return perPage;
    }

    public void setPerPage(int perPage) {
        this.perPage = perPage;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public List<String> getLinks() {
        return links;
    }

    public void setLinks(List<String> links) {
        this.links = links;
    }
}
