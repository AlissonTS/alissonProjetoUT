package br.ufsm.ceesp.ceee.beans;

import br.ufsm.ceesp.ceee.model.Transformador;
import br.ufsm.ceesp.ceee.util.GeradorData;
import br.ufsm.ceesp.ceee.util.LeitorCSV;

import javax.swing.*;
import java.io.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.ArrayList;

public class ConversorCEEE {

    public static void main(String[] args) throws Exception {

        InputStream arquivo = null;
        File f = null;
        JFileChooser fc = new JFileChooser();
        int returnVal = fc.showOpenDialog(new JFrame());
        if (returnVal == JFileChooser.APPROVE_OPTION) {
            f = fc.getSelectedFile();
            //This is where a real application would open the file.
            arquivo = new FileInputStream(f);
        }

        // Funcionando em CSV e TXT

        LeitorCSV csv = new LeitorCSV(arquivo);

        String[] retorno = null;

        retorno = csv.nextLine(true);
        retorno = csv.nextLine(true);

        ArrayList<Transformador> lista = new ArrayList<Transformador>();

        System.out.println("Carregando Linhas...");
        do{
            if(retorno.length>0){

                String tr = retorno[0];

                if(tr.length()>0){
                    Transformador transformador = new Transformador();

                    transformador.setId(new Long(retorno[0]));
                    transformador.setLocalizacao(retorno[6]);

                    transformador.setKvan(new Float(retorno[8].replace(',', '.')));
                    transformador.setPotCTE(new Float(retorno[30].replace(',', '.')));
                    transformador.setImpCTE(new Float(retorno[31].replace(',', '.')));
                    transformador.setKvar(new Float(retorno[36].replace(',', '.')));
                    transformador.setKw(new Float(retorno[37].replace(',', '.')));

                    transformador.setClientes(new Integer(retorno[40]));

                    lista.add(transformador);
                }
            }
            retorno = csv.nextLine(true);
        }while(retorno!=null);


        File saida = new File(f.getAbsolutePath() + ".ceee.exp");
        if(!saida.exists()) {
            saida.createNewFile();
            System.out.println("Arquivo criado..");
        }
        else{
            System.out.println("Arquivo Já criado..");
        }

        try {
            // Começo da escrita do arquivo.
            FileWriter fw = new FileWriter(saida);

            GeradorData gd = new GeradorData();
            String dataFormatada = gd.dataFormatada();
            String horaFormatada = gd.horaFormatada();
            String dataFormatadaReferencia = gd.dataFormatadaReferencia();

            fw.write("PRI;\r\n");
            fw.write(dataFormatada+" "+horaFormatada+"- v2.89;\r\n");
            fw.write("DATA DE REFERÊNCIA = "+dataFormatadaReferencia+";\r\n");

            fw.write("VER;\r\n4.0;\r\n\r\n");

            NumberFormat nf = new DecimalFormat("#0.000");

            fw.write("INSTAL_TRAFO;\r\n");
            for(Transformador tr : lista){
                fw.write("PAL - "+tr.getId()+"; \t"+ tr.getLocalizacao()+"; \t"+Float.toString(tr.getKvan()).replace('.', ',')+"; \t"+Float.toString(tr.getPotCTE()).replace('.', ',')+"; " +
                        "\t"+Float.toString(tr.getImpCTE()).replace('.', ',')+"; " +
                        "\t "+Float.toString(tr.getKvar()).replace('.', ',')+"; \t"+Float.toString(tr.getKw()).replace('.', ',')+"; \t"+tr.getClientes()+";\r\n");
            }

            fw.write("END;\r\n");

            fw.close();
            System.out.println("Arquivo preenchido..");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
