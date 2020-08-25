package com.itus.u_money.presenter;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;

import com.itus.u_money.App;
import com.itus.u_money.contract.TypeContract;
import com.itus.u_money.model.AppDatabase;
import com.itus.u_money.model.TransactionType;
import com.itus.u_money.model.dao.TransactionTypeDAO;

import java.util.List;

public class TypePresenter implements TypeContract.Presenter, Observer<List<TransactionType>> {
   private final TypeContract.View view;
   LiveData<List<TransactionType>> listLiveData;
   String choosingType;

   public TypePresenter(TypeContract.View view) {
      this.view = view;
   }

   @Override
   public void onChanged(List<TransactionType> transactionTypeList) {
      if (choosingType.equalsIgnoreCase("ADD_TRANSACTION"))
         transactionTypeList.remove(0);
      view.showData(transactionTypeList);
      listLiveData.removeObserver(this);
   }
   @Override
   public void getData(int index) {
      TransactionTypeDAO dao = AppDatabase.getDatabase(App.getContext())
                                          .transactionTypeDAO();
      listLiveData = dao.getByGroupId(index);
      listLiveData.observeForever(this);
   }

   @Override
   public void setType(String string) {
      this.choosingType = string;
   }
}
