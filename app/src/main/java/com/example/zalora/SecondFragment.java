package com.example.zalora;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.example.zalora.adapter.ItemAdapter;
import com.example.zalora.R;
import com.example.zalora.model.Response;
import com.example.zalora.network.RetrieveDataSortedTask;
import com.example.zalora.network.RetrieveDataTask;

public class SecondFragment extends Fragment implements PropertyChangeListener {
	public final static String TAG = SecondFragment.class.getSimpleName();
	private AppCompatActivity mainFragmentActivity;
	@SuppressWarnings("unused")
	private Bundle arguments;
	private ListView list;
	private BaseAdapter baseAdapter = null;
	private RetrieveDataTask retrieveDataTask;
	private RetrieveDataSortedTask retrieveDataSortedTask;
	private Spinner txFilter;

	public static SecondFragment init(Bundle args) {
		SecondFragment f = new SecondFragment();
		f.setArguments(args);
		return f;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mainFragmentActivity = (AppCompatActivity) getActivity();
		setHasOptionsMenu(true);
		arguments = getArguments();
	}

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View v = inflater.inflate(R.layout.fragment_list, container, false);
		list = (ListView) v.findViewById(R.id.list);
        list.setEmptyView(v.findViewById(android.R.id.empty));
		txFilter = initializeSpinner(v);
		baseAdapter = new ItemAdapter(mainFragmentActivity);
        list.setAdapter(baseAdapter);
		return v;
	}
	
	private Spinner initializeSpinner(View view) {
		Spinner spinner = (Spinner) view.findViewById(R.id.txFilter);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
					R.array.filter, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);		
		spinner.setOnItemSelectedListener(new OnItemSelectedListener() {

			@Override
			public void onItemSelected(AdapterView<?> parent,
					View view, int pos, long id) {
				filterItems(pos);			
			}

			@Override
			public void onNothingSelected(AdapterView<?> parent) {				
			}
		});	
		return spinner;
	}
	
	private void filterItems(int pos) {
		switch (pos) {
		case 0:
			executeRetrieveDataSortedTask("name");
			break;
		case 1:
			executeRetrieveDataSortedTask("price");
			break;
		case 2:
			executeRetrieveDataSortedTask("brand");
			break;
		case 3:
			executeRetrieveDataSortedTask("popularity");
			break;
			
		default:
			break;
		}
	}
	
	@Override
	public void onViewCreated(View view, Bundle savedInstanceState) {
		super.onViewCreated(view, savedInstanceState);
		if (ApplicationStatus.getInstance().getResults() == null
				|| ApplicationStatus.getInstance().getResults().size() == 0) {
            executeRetrieveDataTask();
		}
//        else {
//			setupListView();
//		}
	}

	@Override
	public void onDestroyView() {
		super.onDestroyView();
		if (retrieveDataTask != null) {
			retrieveDataTask.cancel(true);
			retrieveDataTask = null;
		}
		if (retrieveDataSortedTask != null) {
			retrieveDataSortedTask.cancel(true);
			retrieveDataSortedTask = null;
		}
	}

	private void setupListView() {
		list.setAdapter(baseAdapter);
	}

	private void executeRetrieveDataTask() {
		retrieveDataTask = new RetrieveDataTask(getActivity());
		retrieveDataTask.addPropertyChangeListener(this);
		retrieveDataTask.execute();
	}
	
	private void executeRetrieveDataSortedTask(String param) {
		retrieveDataSortedTask = new RetrieveDataSortedTask(getActivity());
		retrieveDataSortedTask.addPropertyChangeListener(this);
		retrieveDataSortedTask.execute(param);
	}

	@Override
	public void propertyChange(PropertyChangeEvent propertyChangeEvent) {
		if (propertyChangeEvent.getPropertyName().equalsIgnoreCase(
				RetrieveDataTask.PROPERTY_NAME)) {
			Response response = (Response) propertyChangeEvent.getNewValue();
			if (((MainActivity) getActivity()).isCodeSuccessful(response)) {
				ApplicationStatus.getInstance().setResults(response.getMetadata().getResults());
                if(baseAdapter != null) {
                    baseAdapter.notifyDataSetChanged();
                }
			}
		}
		if (propertyChangeEvent.getPropertyName().equalsIgnoreCase(
				RetrieveDataSortedTask.PROPERTY_NAME)) {
			Response response = (Response) propertyChangeEvent.getNewValue();
			if (((MainActivity) getActivity()).isCodeSuccessful(response)) {
				ApplicationStatus.getInstance().setResults(response.getMetadata().getResults());
				if(baseAdapter != null) {
                    baseAdapter.notifyDataSetChanged();
                }
			}
		}
	}
	
	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		super.onCreateOptionsMenu(menu, inflater);
		inflater.inflate(R.menu.main, menu);
	}

	@Override
	public void onPrepareOptionsMenu(Menu menu) {
		super.onPrepareOptionsMenu(menu);
		boolean drawerOpen = ((MainActivity) getActivity()).isDrawerOpen();
		menu.findItem(R.id.menu_refresh).setVisible(!drawerOpen);		
	}

	/**
	 * Respond to user gestures on the ActionBar.
	 */
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.menu_refresh:
			executeRetrieveDataTask();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

}
