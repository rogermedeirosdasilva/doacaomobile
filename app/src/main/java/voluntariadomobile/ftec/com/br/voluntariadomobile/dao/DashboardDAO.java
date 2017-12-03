package voluntariadomobile.ftec.com.br.voluntariadomobile.dao;

import android.content.Context;

import java.util.ArrayList;

import voluntariadomobile.ftec.com.br.voluntariadomobile.bol.CartaoCampanha;
import voluntariadomobile.ftec.com.br.voluntariadomobile.util.BaseDAO;

/**
 * Created by roger on 02/12/17.
 */

public class DashboardDAO extends BaseDAO {
    public DashboardDAO(Context ctx) {
        super(ctx);
    }

    public ArrayList<CartaoCampanha> PegaTodos() {
        ArrayList<CartaoCampanha> retorno = new ArrayList<>();

        CartaoCampanha c = new CartaoCampanha();
        c.setAfinidade(6);
        c.setTitulo("Adoção de Cães e Gatos");
        c.setDescricao("Oportunidade para adoção de cãe e gatos vacinados.");
        c.setImagem("https://novafriburgo.rj.gov.br/nova/wp-content/uploads/2017/04/ado%C3%A7%C3%A3o-de-c%C3%A3o-400x230.jpg");
        retorno.add(c);

        c = new CartaoCampanha();
        c.setAfinidade(8);
        c.setTitulo("Lar de Idosos São João");
        c.setDescricao("O Lar de idosos São João está arrecadando alimentos e presentes de natal para o final de ano.");
        c.setImagem("http://www.carreiradoadvogado.com.br/wp-content/uploads/2017/03/idoso.jpg");
        retorno.add(c);

        c = new CartaoCampanha();
        c.setAfinidade(3);
        c.setTitulo("Lar São Jorge");
        c.setDescricao("O Lar de idosos São Jorge está arrecadando alimentos e presentes de natal para o final de ano.");
        c.setImagem("http://www.jornaldaorla.com.br/arquivos/noticia/2016_10_10_13_13_27_7648.jpg");
        retorno.add(c);

        return retorno;
    }
}
