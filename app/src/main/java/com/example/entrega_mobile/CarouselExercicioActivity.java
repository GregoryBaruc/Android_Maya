package com.example.entrega_mobile;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;
import java.util.ArrayList;
import java.util.List;

/**
 * Activity responsável pela exibição dos exercícios em formato de carrossel.
 * Utiliza ViewPager2 para permitir a navegação entre as imagens das etapas.
 */
public class CarouselExercicioActivity extends AppCompatActivity {

    // Declaração dos componentes para controle do carrossel
    private ViewPager2 viewPager;
    private CarouselAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Inflagem do layout específico do carrossel
        setContentView(R.layout.exercicios_carrousel);

        // Extração dos dados enviados pela Activity anterior (título e lista de imagens)
        String titulo = getIntent().getStringExtra("TITULO_EXERCICIO");
        int[] imagensIds = getIntent().getIntArrayExtra("IMAGENS_EXERCICIO");

        // Atualização dinâmica do título da tela
        TextView txtTitulo = findViewById(R.id.tituloCarousel);
        if (titulo != null && txtTitulo != null) {
            txtTitulo.setText(titulo.toUpperCase());
        }

        // Configuração inicial do ViewPager2
        viewPager = findViewById(R.id.viewPagerCarousel);
        
        List<Integer> images = new ArrayList<>();
        if (imagensIds != null) {
            // Conversão do array de IDs para uma lista utilizável pelo Adapter
            for (int id : imagensIds) {
                images.add(id);
            }
        }

        // Instanciação e definição do Adapter para o carrossel
        adapter = new CarouselAdapter(images);
        viewPager.setAdapter(adapter);

        // Listener para o botão de retorno
        ImageView btnVoltar = findViewById(R.id.btnVoltarCarousel);
        if (btnVoltar != null) {
            btnVoltar.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

        // Listener para o botão de conclusão do exercício
        Button btnConcluir = findViewById(R.id.btnConcluir);
        if (btnConcluir != null) {
            btnConcluir.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    finish();
                }
            });
        }

        /*
         * Implementação dos controles manuais de navegação (setas).
         */
        ImageView btnPrev = findViewById(R.id.btnPrev);
        ImageView btnNext = findViewById(R.id.btnNext);

        // Ação para retroceder uma imagem
        if (btnPrev != null) {
            btnPrev.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int currentItem = viewPager.getCurrentItem();
                    if (currentItem > 0) {
                        viewPager.setCurrentItem(currentItem - 1);
                    }
                }
            });
        }

        // Ação para avançar uma imagem
        if (btnNext != null) {
            btnNext.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int currentItem = viewPager.getCurrentItem();
                    if (currentItem < images.size() - 1) {
                        viewPager.setCurrentItem(currentItem + 1);
                    }
                }
            });
        }
        
        /* 
         * Registro de callback para monitorar a mudança de páginas.
         * Utilizado para controlar a visibilidade das setas de navegação.
         */
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                // Lógica para ocultar setas nos limites do carrossel
                if (btnPrev != null) btnPrev.setVisibility(position == 0 ? View.GONE : View.VISIBLE);
                if (btnNext != null) btnNext.setVisibility(position == images.size() - 1 ? View.GONE : View.VISIBLE);
            }
        });
    }

    /**
     * Adapter interno utilizando o padrão ViewHolder para otimização de performance.
     * Gerencia a vinculação das imagens aos itens do carrossel.
     */
    private static class CarouselAdapter extends RecyclerView.Adapter<CarouselAdapter.ViewHolder> {
        private final List<Integer> images;

        CarouselAdapter(List<Integer> images) {
            this.images = images;
        }

        @NonNull
        @Override
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            // Infla o layout de cada item individual do carrossel
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_carousel, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
            // Atribui o recurso de imagem ao componente ImageView do item
            holder.imageView.setImageResource(images.get(position));
        }

        @Override
        public int getItemCount() {
            return images.size();
        }

        /**
         * ViewHolder que mantém a referência para os componentes de cada item da lista.
         */
        static class ViewHolder extends RecyclerView.ViewHolder {
            ImageView imageView;
            ViewHolder(View itemView) {
                super(itemView);
                imageView = itemView.findViewById(R.id.imgCarousel);
            }
        }
    }
}