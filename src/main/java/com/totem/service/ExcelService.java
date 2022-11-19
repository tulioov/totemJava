package com.totem.service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.totem.dto.FiltroPesquisaMonitoracaoDTO;
import com.totem.entity.Monitoracao;

@Service
public class ExcelService {

	@Autowired
	MonitoracaoService monitoracaoService;

	private static final Logger logger = LogManager.getLogger(ExcelService.class);

	public ByteArrayInputStream geraPlanilhaCompleta(String emailUsuario,
			FiltroPesquisaMonitoracaoDTO filtroPesquisaMonitoracaoDTO) {

		List<Monitoracao> lstMonitoracao = monitoracaoService.listarMonitoracaoByFiltro(emailUsuario,
				filtroPesquisaMonitoracaoDTO);

		try {

			HSSFWorkbook workbook = new HSSFWorkbook();
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			
			HSSFSheet sheet = workbook.createSheet("Relatorio");
			sheet.setDisplayGridlines(false);

			headerExcel(workbook, sheet);
			bodyExcel(workbook,sheet, lstMonitoracao);

			autoSizeColumn(sheet, 16);

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		} catch (Exception e) {
			logger.error("Error geraPlanilhaCompleta ", e);
			return null;
		}

	}

	private void bodyExcel(HSSFWorkbook workbook, HSSFSheet sheet, List<Monitoracao> lstMonitoracao) {

		int index = 1;
		CellStyle cellStyle = workbook.createCellStyle();
		cellStyle.setBorderTop(BorderStyle.MEDIUM);
		cellStyle.setBorderBottom(BorderStyle.MEDIUM);
		cellStyle.setBorderLeft(BorderStyle.MEDIUM);
		cellStyle.setBorderRight(BorderStyle.MEDIUM);
		
		for (Monitoracao monitoracao : lstMonitoracao) {
			Row row = sheet.createRow(index++);
			Cell matricula = row.createCell(0);
			matricula.setCellStyle(cellStyle);
			matricula.setCellValue(monitoracao.getUsuario().getMatricula());
			
			Cell nome = row.createCell(1);
			nome.setCellStyle(cellStyle);
			nome.setCellValue(monitoracao.getUsuario().getNome());
			
			Cell atividade = row.createCell(1);
			atividade.setCellStyle(cellStyle);
			atividade.setCellValue(monitoracao.getAtividade().getNome());
			
			Cell tempoDecorrido = row.createCell(1);
			tempoDecorrido.setCellStyle(cellStyle);
			tempoDecorrido.setCellValue(monitoracao.getTempoTrabalhoFimIni());
			
			Cell status = row.createCell(1);
			status.setCellStyle(cellStyle);
			status.setCellValue(monitoracao.getStatusMonitoracao().getNome());
		}
	}

	private void headerExcel(HSSFWorkbook workbook, HSSFSheet sheet) {

		HSSFFont headerFont = workbook.createFont();
		headerFont.setBold(true);
		
		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);
		headerCellStyle.setBorderTop(BorderStyle.MEDIUM);
		headerCellStyle.setBorderBottom(BorderStyle.MEDIUM);
		headerCellStyle.setBorderLeft(BorderStyle.MEDIUM);
		headerCellStyle.setBorderRight(BorderStyle.MEDIUM);
		
		Row headerRow = sheet.createRow(0);

		Cell matricula = headerRow.createCell(0);
		matricula.setCellValue("Matricula");
		matricula.setCellStyle(headerCellStyle);
		
		Cell nome = headerRow.createCell(1);
		nome.setCellValue("Nome");
		nome.setCellStyle(headerCellStyle);
		
		Cell atividade = headerRow.createCell(2);
		atividade.setCellValue("Atividade");
		atividade.setCellStyle(headerCellStyle);
		
		Cell tempoDecorrido = headerRow.createCell(3);
		tempoDecorrido.setCellValue("TempoDecorrido");
		tempoDecorrido.setCellStyle(headerCellStyle);
		
		Cell status = headerRow.createCell(4);
		status.setCellValue("Status");
		status.setCellStyle(headerCellStyle);

	}

	private void autoSizeColumn(HSSFSheet sheet, int index) {
		for (int columnIndex = 0; columnIndex < index; columnIndex++) {
			sheet.autoSizeColumn(columnIndex);
		}
	}

}
