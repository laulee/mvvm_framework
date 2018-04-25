package com.framework.core.recycler;

import android.databinding.ObservableArrayList;
import android.databinding.ObservableList;

import com.framework.core.base.BaseVM;

import java.util.List;

import me.tatarka.bindingcollectionadapter2.ItemBinding;
import me.tatarka.bindingcollectionadapter2.OnItemBind;

/**
 * 作者：Created by Laulee
 * 时间：2018/4/24.
 */

public class BaseRecyclerVM extends BaseVM {

    //给Recyclerview添加items
    public ObservableList<ItemVar> items = new ObservableArrayList();

    //为每个item添加itemview
    public OnItemBind<ItemVar> itemBinding = new OnItemBind<ItemVar>() {
        @Override
        public void onItemBind(ItemBinding itemBinding, int position, ItemVar item) {
            itemBinding.set(item.getVariableId(), item.getLayoutId());
        }
    };

    /**
     * @param itemVar
     */
    public void addItem(ItemVar itemVar) {
        items.add(itemVar);
    }

    /**
     * @param itemVars
     */
    public void addItems(List<ItemVar> itemVars) {
        items.addAll(itemVars);
    }
}