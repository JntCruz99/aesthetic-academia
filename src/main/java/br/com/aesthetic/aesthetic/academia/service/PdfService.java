package br.com.aesthetic.aesthetic.academia.service;

import br.com.aesthetic.aesthetic.academia.domain.model.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.*;
import org.springframework.beans.factory.annotation.Value;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

@Service
@Slf4j
public class PdfService {

    @Value("${pdf.output.directory}")
    private String pdfOutputDirectory; //pdfOutputDirectory +"/BOASVINDAS_TEMP.pdf"

    public void gerarBoasVindasPdf(Aluno aluno, String nomeArquivo) {
        log.info("gerando pdf");
        nomeArquivo = nomeArquivo + ".pdf";
        String caminhoCompleto = pdfOutputDirectory + File.separator + nomeArquivo;

        try {
            // Abra o documento e leia o PDF modelo
            PdfReader pdfReader = new PdfReader(pdfOutputDirectory + "/BOASVINDAS_TEMP.pdf");
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(caminhoCompleto));
            document.open();
            PdfContentByte cb = writer.getDirectContent();

            // Adicione páginas do PDF modelo
            for (int i = 1; i <= pdfReader.getNumberOfPages(); i++) {
                PdfImportedPage page = writer.getImportedPage(pdfReader, i);
                document.newPage();
                cb.addTemplate(page, 0, 0);
            }

            // Adicione informações do aluno nas coordenadas especificadas
            cb.beginText();
            cb.setFontAndSize(BaseFont.createFont(), 12); // Escolha a fonte e o tamanho desejados
            cb.setTextMatrix(150, 760); // Coordenadas X e Y
            cb.showText(aluno.getNome());
            cb.setTextMatrix(200, 555); // Coordenadas X e Y
            cb.showText(aluno.getNome());
            cb.setTextMatrix(200, 536);
            cb.showText(String.valueOf(aluno.getIdade()));
            cb.setTextMatrix(200, 520);
            cb.showText(aluno.getEndereco());
            cb.setTextMatrix(200, 505);
            cb.showText(aluno.getEmail());
            cb.setTextMatrix(200, 490);
            cb.showText(aluno.getDataDeCadastro());
            cb.setTextMatrix(200, 470);
            cb.showText(aluno.getMatricula());
            cb.endText();

            document.close();
            pdfReader.close();

            log.info("pdf gerado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deletarPDF(String nomeArquivo) {
        nomeArquivo = nomeArquivo + ".pdf";
        String caminhoCompleto = pdfOutputDirectory + File.separator + nomeArquivo;
        File arquivo = new File(caminhoCompleto);

        if (arquivo.exists()) {
            if (arquivo.delete()) {
                log.info("PDF deletado com sucesso: " + caminhoCompleto);
            } else {
                log.error("Não foi possível deletar o PDF: " + caminhoCompleto);
            }
        } else {
            log.warn("O PDF não existe: " + caminhoCompleto);
        }
    }

    public void gerarPdfsDietas(Aluno aluno, List<Dieta> dietas, String nomeArquivo) {
        log.info("Gerando PDF de dietas");
        nomeArquivo = nomeArquivo + ".pdf";
        String caminhoCompleto = pdfOutputDirectory + File.separator + nomeArquivo;

        try {
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(caminhoCompleto));
            document.open();
            PdfContentByte cb = writer.getDirectContent();

            // Adicione o cabeçalho
            adicionarCabecalhoDietas(document, aluno);

            // Adicione informações sobre as dietas
            adicionarDietas(document, dietas);

            document.close();
            log.info("PDF de dietas gerado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void adicionarCabecalhoDietas(Document document, Aluno aluno) throws DocumentException {
        Font fontCabecalho = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
        Paragraph cabecalho = new Paragraph("Dieta do Aluno: " + aluno.getNome(), fontCabecalho);
        cabecalho.setAlignment(Element.ALIGN_CENTER);
        cabecalho.setSpacingAfter(20f);
        document.add(cabecalho);
    }

    private void adicionarDietas(Document document, List<Dieta> dietas) throws DocumentException {
        Font fontTituloDieta = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
        Font fontConteudoDieta = new Font(Font.FontFamily.HELVETICA, 12);

        for (Dieta dieta : dietas) {
            Paragraph tituloDieta = new Paragraph("Dieta " + dieta.getId(), fontTituloDieta);
            tituloDieta.setSpacingBefore(10f);
            document.add(tituloDieta);

            Paragraph descricaoDieta = new Paragraph("Descrição: " + dieta.getDescricao(), fontConteudoDieta);
            Paragraph caloriasDiarias = new Paragraph("Calorias Diárias: " + dieta.getCaloriasDiarias(), fontConteudoDieta);

            document.add(descricaoDieta);
            document.add(caloriasDiarias);

            adicionarAlimentos(document, dieta.getCafeDaManha(), "Café da Manhã", fontConteudoDieta);
            adicionarAlimentos(document, dieta.getLancheDaManha(), "Lanche da Manhã", fontConteudoDieta);
            adicionarAlimentos(document, dieta.getAlmoco(), "Almoço", fontConteudoDieta);
            adicionarAlimentos(document, dieta.getLancheDaTarde(), "Lanche da Tarde", fontConteudoDieta);
            adicionarAlimentos(document, dieta.getJanta(), "Janta", fontConteudoDieta);
            adicionarAlimentos(document, dieta.getPreTreino(), "Pré-Treino", fontConteudoDieta);
            adicionarAlimentos(document, dieta.getPosTreino(), "Pós-Treino", fontConteudoDieta);
        }
    }

    private void adicionarAlimentos(Document document, List<Alimento> alimentos, String refeicao, Font font) throws DocumentException {
        if (alimentos != null && !alimentos.isEmpty()) {
            Paragraph tituloRefeicao = new Paragraph(refeicao, font);
            tituloRefeicao.setSpacingBefore(10f);
            document.add(tituloRefeicao);

            PdfPTable tabelaAlimentos = new PdfPTable(3); // 3 colunas para Nome, Medida e Caloria
            tabelaAlimentos.setWidthPercentage(100);

            PdfPCell cabecalhoNome = new PdfPCell(new Phrase("Nome", font));
            PdfPCell cabecalhoMedida = new PdfPCell(new Phrase("Medida", font));
            PdfPCell cabecalhoCaloria = new PdfPCell(new Phrase("Caloria", font));

            tabelaAlimentos.addCell(cabecalhoNome);
            tabelaAlimentos.addCell(cabecalhoMedida);
            tabelaAlimentos.addCell(cabecalhoCaloria);

            for (Alimento alimento : alimentos) {
                tabelaAlimentos.addCell(new Phrase(alimento.getAlimento(), font));
                tabelaAlimentos.addCell(new Phrase(alimento.getMedida(), font));
                tabelaAlimentos.addCell(new Phrase(String.valueOf(alimento.getCaloria()), font));
            }

            document.add(tabelaAlimentos);
        }
    }

    public void gerarPdfsTreinos(Aluno aluno, List<Treino> treinos, String nomeArquivo) {
        log.info("Gerando PDF de treinos");
        nomeArquivo = nomeArquivo + ".pdf";
        String caminhoCompleto = pdfOutputDirectory + File.separator + nomeArquivo;

        try {
            Document document = new Document();
            PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(caminhoCompleto));
            document.open();

            // Adicione o cabeçalho
            adicionarCabecalho(document, aluno);

            // Adicione informações sobre os treinos
            adicionarTreinos(document, treinos);

            document.close();
            log.info("PDF de treinos gerado");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void adicionarCabecalho(Document document, Aluno aluno) throws DocumentException {
        Font fontCabecalho = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
        Paragraph cabecalho = new Paragraph("Treinos do Aluno: " + aluno.getNome(), fontCabecalho);
        cabecalho.setAlignment(Element.ALIGN_CENTER);
        cabecalho.setSpacingAfter(20f);
        document.add(cabecalho);
    }

    private void adicionarTreinos(Document document, List<Treino> treinos) throws DocumentException {
        Font fontTituloTreino = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
        Font fontConteudoTreino = new Font(Font.FontFamily.HELVETICA, 12);

        for (Treino treino : treinos) {
            Paragraph tituloTreino = new Paragraph("Treino " + treino.getId(), fontTituloTreino);
            tituloTreino.setSpacingBefore(10f);
            document.add(tituloTreino);

            Paragraph dataTreino = new Paragraph("Data: " + treino.getData(), fontConteudoTreino);
            Paragraph descricaoTreino = new Paragraph("Descrição: " + treino.getDescricao(), fontConteudoTreino);
            Paragraph ativoTreino = new Paragraph("Ativo: " + (treino.isAtivo() ? "Sim" : "Não"), fontConteudoTreino);

            document.add(dataTreino);
            document.add(descricaoTreino);
            document.add(ativoTreino);

            adicionarExercicios(document, treino.getExercicio());
        }
    }

    private void adicionarExercicios(Document document, List<Exercicio> exercicios) throws DocumentException {
        Font fontTituloExercicio = new Font(Font.FontFamily.HELVETICA, 12, Font.BOLD);
        Font fontConteudoExercicio = new Font(Font.FontFamily.HELVETICA, 10);

        for (Exercicio exercicio : exercicios) {
            Paragraph tituloExercicio = new Paragraph("Exercício " + exercicio.getId(), fontTituloExercicio);
            tituloExercicio.setSpacingBefore(10f);
            document.add(tituloExercicio);

            Paragraph serieExercicio = new Paragraph("Séries: " + exercicio.getSerie(), fontConteudoExercicio);
            Paragraph repeticoesExercicio = new Paragraph("Repetições: " + exercicio.getRepeticoes(), fontConteudoExercicio);
            Paragraph diaDaSemanaExercicio = new Paragraph("Dia da Semana: " + exercicio.getDiaDaSemana(), fontConteudoExercicio);
            Paragraph tipoExercicio = new Paragraph("Tipo de Exercício: " + exercicio.getTipo().getNome(), fontConteudoExercicio);
            Paragraph professorExercicio = new Paragraph("Professor: " + exercicio.getProfessor().getNome(), fontConteudoExercicio);

            document.add(serieExercicio);
            document.add(repeticoesExercicio);
            document.add(diaDaSemanaExercicio);
            document.add(tipoExercicio);
            document.add(professorExercicio);
        }
    }
}
