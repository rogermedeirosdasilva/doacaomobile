package voluntariadomobile.ftec.com.br.voluntariadomobile.bol;

/**
 * Created by roger on 02/11/17.
 */

public enum ESituacaoCadastro {
    ATIVO(1),
    INATIVO(2),
    NAO_VERIFICADO(3),
    BLOQUEADO(4);

    private int valor;
    ESituacaoCadastro(int valorSituacao) {
        valor = valorSituacao;
    }

    public int getValor(){
        return valor;
    }
}
