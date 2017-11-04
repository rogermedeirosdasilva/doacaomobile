package voluntariadomobile.ftec.com.br.voluntariadomobile.bol;

/**
 * Created by roger on 04/11/17.
 */

public class Afinidade {
    private Integer codigo;
    private String descricao;
    private boolean selecionado;

    public Integer getCodigo() {
        return codigo;
    }

    public void setCodigo(Integer codigo) {
        this.codigo = codigo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public boolean isSelecionado() {
        return selecionado;
    }

    public void setSelecionado(boolean selecionado) {
        this.selecionado = selecionado;
    }
}
