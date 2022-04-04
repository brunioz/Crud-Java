package com.example.crudgames;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ContextMenu.ContextMenuInfo;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.AdapterView.AdapterContextMenuInfo;
import android.widget.SearchView.OnQueryTextListener;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ListarJogadoresActivity extends AppCompatActivity {
    private ListView listView;
    private JogoDAO dao;
    private List<Jogo> jogos;
    private List<Jogo> jogadoresFiltrados = new ArrayList<>();

    public ListarJogadoresActivity() {
    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listar_jogadores);
        listView = findViewById(R.id.lista_jogadores);
        dao = new JogoDAO(this);
        jogos = dao.obterTodos();
        jogadoresFiltrados.addAll(jogos);
        ArrayAdapter<Jogo> adaptador = new ArrayAdapter<Jogo>(this, android.R.layout.simple_list_item_1, jogadoresFiltrados);
        listView.setAdapter(adaptador);
        registerForContextMenu(listView);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_principal, menu);
        SearchView sv = (SearchView)menu.findItem(R.id.app_bar_search).getActionView();
        sv.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            public boolean onQueryTextChange(String s) {
                procurarJogador(s);
                return false;
            }
        });
        return true;
    }

    public void procurarJogador(String nome) {
        jogadoresFiltrados.clear();
        //Iterator var2 = jogos.iterator();

       /* while(var2.hasNext()) {
            Jogo j = (Jogo)var2.next();*/
            for(Jogo j: jogos) {
                if (j.getNome().toLowerCase().contains(nome.toLowerCase())) {
                    jogadoresFiltrados.add(j);
                    // }
                }
            }
        listView.invalidateViews();
    }

    public void excluir(MenuItem item) {
       AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        final Jogo jogadorExcluir = jogadoresFiltrados.get(menuInfo.position);
        AlertDialog dialog = new AlertDialog.Builder(this).setTitle("Atenção").setMessage("Realmente deseja excluir o jogador?").setNegativeButton("NÃO", (OnClickListener)null).setPositiveButton("Sim", new OnClickListener() {
            public void onClick(DialogInterface dialogInterface, int i) {
                jogadoresFiltrados.remove(jogadorExcluir);
                jogos.remove(jogadorExcluir);
                dao.excluir(jogadorExcluir);
                listView.invalidateViews();
            }
        }).create();
        dialog.show();
    }

    public void cadastrar(MenuItem item) {
        Intent it = new Intent(this, CadastroJogadoresActivity.class);
        startActivity(it);
    }

    public void atualizar(MenuItem item) {
        AdapterView.AdapterContextMenuInfo menuInfo = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
        final Jogo jogadorAtualizar = jogadoresFiltrados.get(menuInfo.position);
        Intent it = new Intent(this, CadastroJogadoresActivity.class);
        it.putExtra("jogos", jogadorAtualizar);
        startActivity(it);
    }

    public void onResume() {
        super.onResume();
        jogos = dao.obterTodos();
        jogadoresFiltrados.clear();
        jogadoresFiltrados.addAll(jogos);
        listView.invalidateViews();
    }

    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater i = getMenuInflater();
        i.inflate(R.menu.menu_contexto, menu);
    }
}