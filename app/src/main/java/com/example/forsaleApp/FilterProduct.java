package com.example.forsaleApp;

import android.widget.Filter;

import com.example.forsaleApp.Adapters.AdapterHomeProducts;

import java.util.ArrayList;

public class FilterProduct extends Filter {

    private AdapterHomeProducts adapter;
    private ArrayList<ModelProduct> filterList;

    public FilterProduct(AdapterHomeProducts adapter, ArrayList<ModelProduct> filterList) {
        this.adapter = adapter;
        this.filterList = filterList;
    }

    @Override
    protected FilterResults performFiltering(CharSequence charSequence) {
        FilterResults results = new FilterResults();
        //validate data for search query
        if (charSequence != null && charSequence.length() > 0)
        {
            //search filed not empty, search something, perform search

            //change to upper case, to make case insensitive
            charSequence = charSequence.toString().toUpperCase();
            //store our filtered list
            ArrayList<ModelProduct> filteredModels = new ArrayList<>();
            for (int i=0 ; i<filterList.size() ; i++)
            {
                //check, search by title and category
                if (filterList.get(i).getProductName().toUpperCase().contains(charSequence) ||
                filterList.get(i).getProductDescription().toUpperCase().contains(charSequence) ||
                        filterList.get(i).getProductCategory().toUpperCase().contains(charSequence))
                {
                    //add filter data to list
                    filteredModels.add(filterList.get(i));
                }
            }
            results.count = filteredModels.size();
            results.values = filteredModels;
        }
        else
        {
            //search filed empty, not searching, return original//all//complete list
            results.count = filterList.size();
            results.values = filterList;
        }
        return results;
    }

    @Override
    protected void publishResults(CharSequence charSequence, FilterResults filterResults) {

        adapter.productList = (ArrayList<ModelProduct>) filterResults.values;
        //refresh adapter
        adapter.notifyDataSetChanged();

    }
}
