package com.phong.baitaptugiaimenu_2;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;

import com.phong.model.SanPham;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lvSanPham;
    ArrayAdapter<SanPham> sanPhamArrayAdapter;
    SanPham selectedSP = null;
    ArrayList<SanPham> dsNguon = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addControls();
        addEvents();
    }

    private void addEvents() {
        lvSanPham.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedSP = sanPhamArrayAdapter.getItem(i);
            }
        });
        lvSanPham.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                selectedSP = sanPhamArrayAdapter.getItem(i);
                return false;
            }
        });
    }

    private void addControls() {
        lvSanPham = (ListView) findViewById(R.id.lvSanPham);
        sanPhamArrayAdapter = new ArrayAdapter<SanPham>(
                MainActivity.this,
                android.R.layout.simple_list_item_1
        );
        lvSanPham.setAdapter(sanPhamArrayAdapter);
        registerForContextMenu(lvSanPham);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        getMenuInflater().inflate(R.menu.context_menu, menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.menuDetails)
        {
            thongTinChiTiet();
        }
        return super.onContextItemSelected(item);
    }

    private void thongTinChiTiet() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.detail_edit_layout);
        dialog.setTitle("Thông tin chi tiết");

        final EditText edtId = (EditText) dialog.findViewById(R.id.edtId);
        final EditText edtName = (EditText) dialog.findViewById(R.id.edtName);
        final EditText edtPrice = (EditText) dialog.findViewById(R.id.edtPrice);
        Button btnRemove = (Button) dialog.findViewById(R.id.btnRemove);
        Button btnBack = (Button) dialog.findViewById(R.id.btnBack);
        edtId.setText(selectedSP.getId());
        edtName.setText(selectedSP.getName());
        edtPrice.setText(selectedSP.getPrice());
        btnRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sanPhamArrayAdapter.remove(selectedSP);
                dialog.dismiss();
                dsNguon.clear();
                for (int i = 0; i < sanPhamArrayAdapter.getCount(); i++)
                {
                    dsNguon.add(sanPhamArrayAdapter.getItem(i));
                }
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.option_menu, menu);
        MenuItem menuSearch = menu.findItem(R.id.menuSearch);
        SearchView searchView = (SearchView) menuSearch.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s.isEmpty())
                {
                    sanPhamArrayAdapter.clear();
                    sanPhamArrayAdapter.addAll(dsNguon);
                }
                else {
                    ArrayList<SanPham> dsTim = new ArrayList<SanPham>();
                    for (SanPham sp : dsNguon) {
                        if (sp.getId().contains(s) || sp.getName().contains(s)) {
                            dsTim.add(sp);
                        }
                    }
                    sanPhamArrayAdapter.clear();
                    sanPhamArrayAdapter.addAll(dsTim);
                }
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId())
        {
            case R.id.menuAdd:
                hienThiManHinhNhapSanPham();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    private void hienThiManHinhNhapSanPham() {
        final Dialog dialog = new Dialog(MainActivity.this);
        dialog.setContentView(R.layout.detail_layout);
        dialog.setTitle("Nhập mới");

        final EditText edtId = (EditText) dialog.findViewById(R.id.edtId);
        final EditText edtName = (EditText) dialog.findViewById(R.id.edtName);
        final EditText edtPrice = (EditText) dialog.findViewById(R.id.edtPrice);
        Button btnSave = (Button) dialog.findViewById(R.id.btnSave);
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SanPham sp = new SanPham();
                sp.setId(edtId.getText().toString());
                sp.setName(edtName.getText().toString());
                sp.setPrice(edtPrice.getText().toString());
                sanPhamArrayAdapter.add(sp);
                dialog.dismiss();
                dsNguon.clear();
                for (int i = 0; i < sanPhamArrayAdapter.getCount(); i++)
                {
                    dsNguon.add(sanPhamArrayAdapter.getItem(i));
                }
            }
        });
        dialog.show();
    }
}
