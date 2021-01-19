import { Injectable } from '@angular/core';
import * as fs from 'file-saver';
import * as ExcelJS from 'exceljs/dist/exceljs.min.js';
import { DatePipe } from 'node_modules/@angular/common';
import { TranslateService } from '@ngx-translate/core';

const alphaBet = 'ABCDEFGHIJKLMNOPQRSTUVWXYZ';
// tslint:disable-next-line:max-line-length
const logoBase64 = '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';
const backgroundColorGreen = {
  type: 'pattern',
  pattern: 'solid',
  fgColor: { argb: 'ccffcc' }
};
const backgroundColorGray = {
  type: 'pattern',
  pattern: 'solid',
  fgColor: { argb: 'bfbfbf' }
};
const borderFull = {
  top: { style: 'thin' },
  left: { style: 'thin' },
  bottom: { style: 'thin' },
  right: { style: 'hair' }
};
const borderFullThin = {
  top: { style: 'thin' },
  left: { style: 'thin' },
  bottom: { style: 'thin' },
  right: { style: 'thin' }
};
const borderLeft = {
  top: { style: 'thin' },
  bottom: { style: 'thin' },
  right: { style: 'thin' }
};
const alignMid = {
  vertical: 'middle'
};
const alignTop = {
  vertical: 'top'
};
const alignMidRight = {
  vertical: 'middle',
  horizontal: 'right'
};
const alignMidLeft = {
  vertical: 'middle',
  horizontal: 'left'
};
const alignTopRight = {
  vertical: 'top',
  horizontal: 'right'
};
const alignTopCenter = {
  vertical: 'top',
  horizontal: 'center'
};
const alignMidCenter = {
  vertical: 'middle',
  horizontal: 'center'
};
const wrapTextTopLeft = {
  vertical: 'top',
  wrapText: true
};
const wrapTextMid = {
  vertical: 'middle',
  horizontal: 'center',
  wrapText: true
};
const wrapTextTopCenter = {
  vertical: 'top',
  horizontal: 'center',
  wrapText: true
};
const fontName = 'ＭＳ ゴシック';
const fontSize16 = 16;
const fontSize9 = 9;
const BI = 'BI';
const BD = 'BD';
const DD = 'DD';
const PM = 'PM';
const UT = 'UT';
const PT = 'PT';
const OP = 'OP';

@Injectable({
  providedIn: 'root'
})
export class ExportExcelService {

  constructor(
    private datePipe: DatePipe) { }

  getEmployeeForm(ws: any, data: any): any {
    if (data === null || data === undefined) {
      return null;
    }
    // Init columns
    ws.addRow(Array(56).fill(''));
    ws.getColumn(1).width = 0.5;
    for (let index = 1; index < 56; index++) {
      ws.getColumn(index).width = 3.25;
    }
    // ws.pageSetup.printArea = 'A1:AP10';
    // Design format
    // label
    ws.mergeCells('B2:T3');
    ws.getCell('B2').value = '技術経歴書';
    ws.getCell('B2').font = { name: fontName, size: fontSize16, bold: true };
    // 株式会社ブライセン
    ws.mergeCells('B4:T5');
    ws.getCell('B4').value = '株式会社ブライセン';
    ws.getCell('B4').font = { name: fontName, size: fontSize16, bold: true };
    // 形態 label
    ws.mergeCells('U2:X2');
    ws.getCell('U2').value = '形態';
    ws.getCell('U2').font = { name: fontName, size: fontSize9 };
    ws.getCell('U2').alignment = alignTopRight;
    ws.getCell('U2').border = borderFull;
    ws.getCell('U2').fill = backgroundColorGreen;
    // 一括／実績
    ws.mergeCells('Y2:AP2');
    ws.getCell('Y2').value = '一括／実績';
    ws.getCell('Y2').font = { name: fontName, size: fontSize9 };
    ws.getCell('Y2').alignment = alignTop;
    ws.getCell('Y2').border = borderLeft;
    // 担当作業: label
    ws.mergeCells('U3:X4');
    ws.getCell('U3').value = '担当作業:';
    ws.getCell('U3').font = { name: fontName, size: fontSize9 };
    ws.getCell('U3').alignment = alignTopRight;
    ws.getCell('U3').border = borderFull;
    ws.getCell('U3').fill = backgroundColorGreen;
    // BI:基本検討 ／BD:基本設計／DD:詳細設計／PM:プログラム製造
    // UT:単体試験 ／PT:結合･総合試験 ／OP:運用･保守
    ws.mergeCells('Y3:AP4');
    ws.getCell('Y3').value = 'BI:基本検討 ／BD:基本設計／DD:詳細設計／PM:プログラム製造\r\nUT: 単体試験 ／PT: 結合･総合試験 ／OP: 運用･保守 ';
    ws.getCell('Y3').font = { name: fontName, size: fontSize9 };
    ws.getCell('Y3').alignment = wrapTextTopLeft;
    // ws.getCell('Y3').alignment = alignTop;
    ws.getCell('Y3').border = borderLeft;
    // 役割: label
    ws.mergeCells('U5:X5');
    ws.getCell('U5').value = '役割:';
    ws.getCell('U5').font = { name: fontName, size: fontSize9 };
    ws.getCell('U5').alignment = alignTopRight;
    ws.getCell('U5').border = borderFull;
    ws.getCell('U5').fill = backgroundColorGreen;
    // BI:基本検討 ／BD:基本設計／DD:詳細設計／PM:プログラム製造
    // UT:単体試験 ／PT:結合･総合試験 ／OP:運用･保守
    ws.mergeCells('Y5:AP5');
    ws.getCell('Y5').value = 'PL:ﾌﾟﾛｼﾞｪｸﾄﾘｰﾀﾞｰ ／L:ﾘｰﾀﾞｰ ／SL:ｻﾌﾞﾘｰﾀﾞｰ ／M:ﾒﾝﾊﾞｰ';
    ws.getCell('Y5').font = { name: fontName, size: fontSize9 };
    ws.getCell('Y5').alignment = alignTop;
    ws.getCell('Y5').border = borderLeft;
    // today
    ws.mergeCells('AL6:AN6');
    ws.getCell('AL6').value = { formula: '=TODAY()' };
    ws.getCell('AL6').numFmt = 'mm/dd/yyyy';
    ws.getCell('AL6').font = { name: fontName, size: fontSize9 };
    ws.getCell('AL6').alignment = alignTopCenter;
    ws.mergeCells('AO6:AP6');
    // label
    ws.getCell('AO6').value = '現在';
    ws.getCell('AO6').font = { name: fontName, size: fontSize9 };
    ws.getCell('AO6').alignment = alignTopCenter;
    // set height row 7th
    ws.getRow(7).height = 30;
    // 氏名: label
    ws.mergeCells('B7:E7');
    ws.getCell('B7').value = '氏名:';
    ws.getCell('B7').font = { name: fontName, size: fontSize9 };
    ws.getCell('B7').alignment = alignMidRight;
    ws.getCell('B7').border = borderFull;
    ws.getCell('B7').fill = backgroundColorGreen;
    // Name in katakana
    ws.mergeCells('F7:P7');
    ws.getCell('F7').value = data.employeeName;
    ws.getCell('F7').font = { name: fontName, size: fontSize9 };
    ws.getCell('F7').alignment = alignMid;
    ws.getCell('F7').border = borderLeft;
    // 生年月日: birth date label
    ws.mergeCells('Q7:T7');
    ws.getCell('Q7').value = '生年月日:';
    ws.getCell('Q7').font = { name: fontName, size: fontSize9 };
    ws.getCell('Q7').alignment = alignMidRight;
    ws.getCell('Q7').border = borderFull;
    ws.getCell('Q7').fill = backgroundColorGreen;
    // birth date
    ws.mergeCells('U7:Y7');
    ws.getCell('U7').value = new Date(data.employeeBirthdate);
    ws.getCell('U7').font = { name: fontName, size: fontSize9 };
    ws.getCell('U7').alignment = alignMidCenter;
    ws.getCell('U7').border = borderLeft;
    // address label
    ws.mergeCells('Z7:AB7');
    ws.getCell('Z7').value = '最寄駅:';
    ws.getCell('Z7').font = { name: fontName, size: fontSize9 };
    ws.getCell('Z7').alignment = alignMidRight;
    ws.getCell('Z7').border = borderFull;
    ws.getCell('Z7').fill = backgroundColorGreen;
    // address
    ws.mergeCells('AC7:AP7');
    ws.getCell('AC7').value = '';
    ws.getCell('AC7').font = { name: fontName, size: fontSize9 };
    ws.getCell('AC7').alignment = alignMid;
    ws.getCell('AC7').border = borderLeft;
    ws.getCell('AC7').fill = backgroundColorGray;
    // ﾌﾘｶﾞﾅ: label
    ws.getRow(8).height = 40;
    ws.mergeCells('B8:E8');
    ws.getCell('B8').value = 'ﾌﾘｶﾞﾅ:';
    ws.getCell('B8').font = { name: fontName, size: fontSize9 };
    ws.getCell('B8').alignment = alignMidRight;
    ws.getCell('B8').border = borderFull;
    ws.getCell('B8').fill = backgroundColorGreen;
    // Name in Furigana
    ws.mergeCells('F8:P8');
    ws.getCell('F8').value = data.employeeName;
    ws.getCell('F8').font = { name: fontName, size: fontSize9 };
    ws.getCell('F8').alignment = alignMid;
    ws.getCell('F8').border = borderLeft;
    // 年齢: age label
    ws.mergeCells('Q8:T8');
    ws.getCell('Q8').value = '年齢:';
    ws.getCell('Q8').font = { name: fontName, size: fontSize9 };
    ws.getCell('Q8').alignment = alignMidRight;
    ws.getCell('Q8').border = borderFull;
    ws.getCell('Q8').fill = backgroundColorGreen;
    ws.mergeCells('U8:Y8');
    ws.getCell('U8').value = { formula: '=DATEDIF(U7,AL6,"Y")' };
    ws.getCell('U8').numFmt = '# 歳';
    ws.getCell('U8').font = { name: fontName, size: fontSize9 };
    ws.getCell('U8').alignment = alignMidCenter;
    ws.getCell('U8').border = borderLeft;
    // address label
    ws.mergeCells('Z8:AB8');
    ws.getCell('Z8').value = '資格:';
    ws.getCell('Z8').font = { name: fontName, size: fontSize9 };
    ws.getCell('Z8').alignment = alignMidRight;
    ws.getCell('Z8').border = borderFull;
    ws.getCell('Z8').fill = backgroundColorGreen;
    // degree
    ws.mergeCells('AC8:AP8');
    ws.getCell('AC8').value = data.employeeDegree;
    ws.getCell('AC8').font = { name: fontName, size: fontSize9 };
    ws.getCell('AC8').alignment = alignMid;
    ws.getCell('AC8').border = borderLeft;
    // set height row 9th
    ws.getRow(9).height = 30;
    // 社員番号: employee number label
    ws.mergeCells('B9:E9');
    ws.getCell('B9').value = '社員番号:';
    ws.getCell('B9').font = { name: fontName, size: fontSize9 };
    ws.getCell('B9').alignment = alignMidRight;
    ws.getCell('B9').border = borderFull;
    ws.getCell('B9').fill = backgroundColorGreen;
    // employee number
    ws.mergeCells('F9:L9');
    ws.getCell('F9').value = '';
    ws.getCell('F9').font = { name: fontName, size: fontSize9 };
    ws.getCell('F9').alignment = alignMid;
    ws.getCell('F9').border = borderLeft;
    ws.getCell('F9').fill = backgroundColorGray;
    // employee gender label
    ws.mergeCells('M9:N9');
    ws.getCell('M9').value = '性別';
    ws.getCell('M9').font = { name: fontName, size: fontSize9 };
    ws.getCell('M9').alignment = alignMidLeft;
    ws.getCell('M9').border = borderFull;
    ws.getCell('M9').fill = backgroundColorGreen;
    // employee gender:
    ws.mergeCells('O9:P9');
    ws.getCell('O9').value = data.employeeGender === false ? '女' : '男';
    ws.getCell('O9').font = { name: fontName, size: fontSize9 };
    ws.getCell('O9').alignment = alignMidCenter;
    ws.getCell('O9').border = borderLeft;
    // date join company label
    ws.mergeCells('Q9:T9');
    ws.getCell('Q9').value = '入社年月:';
    ws.getCell('Q9').font = { name: fontName, size: fontSize9 };
    ws.getCell('Q9').alignment = alignMidRight;
    ws.getCell('Q9').border = borderFull;
    ws.getCell('Q9').fill = backgroundColorGreen;
    // date join company
    ws.mergeCells('U9:Y9');
    ws.getCell('U9').value = new Date(data.employeeDateJoinCompany);
    ws.getCell('U9').font = { name: fontName, size: fontSize9 };
    ws.getCell('U9').alignment = alignMidCenter;
    ws.getCell('U9').border = borderLeft;
    // blank cell
    ws.mergeCells('Z9:AB9');
    ws.getCell('Z9').value = '最終学歴:';
    ws.getCell('Z9').font = { name: fontName, size: fontSize9 };
    ws.getCell('Z9').alignment = alignMidRight;
    ws.getCell('Z9').border = borderFull;
    ws.getCell('Z9').fill = backgroundColorGreen;
    ws.mergeCells('AC9:AP9');
    ws.getCell('AC9').value = data.employeeUniversity;
    ws.getCell('AC9').font = { name: fontName, size: fontSize9 };
    ws.getCell('AC9').alignment = alignMid;
    ws.getCell('AC9').border = borderLeft;
    // row 10
    ws.getRow(10).height = 40;
    // 番号: STT
    ws.mergeCells('B10:C10');
    ws.getCell('B10').value = '番号';
    ws.getCell('B10').font = { name: fontName, size: fontSize9 };
    ws.getCell('B10').alignment = alignMidCenter;
    ws.getCell('B10').border = borderFull;
    ws.getCell('B10').fill = backgroundColorGreen;
    // 業務内容: business content
    ws.mergeCells('D10:N10');
    ws.getCell('D10').value = '業務内容';
    ws.getCell('D10').font = { name: fontName, size: fontSize9 };
    ws.getCell('D10').alignment = alignMidCenter;
    ws.getCell('D10').border = borderFull;
    ws.getCell('D10').fill = backgroundColorGreen;
    // 開始: start date
    ws.mergeCells('O10:P10');
    ws.getCell('O10').value = '開始';
    ws.getCell('O10').font = { name: fontName, size: fontSize9 };
    ws.getCell('O10').alignment = alignMidCenter;
    ws.getCell('O10').border = borderFull;
    ws.getCell('O10').fill = backgroundColorGreen;
    ws.getCell('O10').numFmt = 'yyyy/MM/dd';
    // 終了: end date
    ws.mergeCells('Q10:R10');
    ws.getCell('Q10').value = '終了';
    ws.getCell('Q10').font = { name: fontName, size: fontSize9 };
    ws.getCell('Q10').alignment = alignMidCenter;
    ws.getCell('Q10').border = borderFull;
    ws.getCell('Q10').fill = backgroundColorGreen;
    // 期間: duration
    ws.mergeCells('S10:U10');
    ws.getCell('S10').value = '期間';
    ws.getCell('S10').font = { name: fontName, size: fontSize9 };
    ws.getCell('S10').alignment = alignMidCenter;
    ws.getCell('S10').border = borderFull;
    ws.getCell('S10').fill = backgroundColorGreen;
    // 形態
    ws.getCell('V10').value = '形\r\n態';
    ws.getCell('V10').font = { name: fontName, size: fontSize9 };
    ws.getCell('V10').alignment = wrapTextMid;
    ws.getCell('V10').border = borderFull;
    ws.getCell('V10').fill = backgroundColorGreen;
    // BI
    ws.getCell('W10').value = 'BI';
    ws.getCell('W10').font = { name: fontName, size: fontSize9 };
    ws.getCell('W10').alignment = wrapTextMid;
    ws.getCell('W10').border = borderFull;
    ws.getCell('W10').fill = backgroundColorGreen;
    // BD
    ws.getCell('X10').value = 'BD';
    ws.getCell('X10').font = { name: fontName, size: fontSize9 };
    ws.getCell('X10').alignment = wrapTextMid;
    ws.getCell('X10').border = borderFull;
    ws.getCell('X10').fill = backgroundColorGreen;
    // DD
    ws.getCell('Y10').value = 'DD';
    ws.getCell('Y10').font = { name: fontName, size: fontSize9 };
    ws.getCell('Y10').alignment = wrapTextMid;
    ws.getCell('Y10').border = borderFull;
    ws.getCell('Y10').fill = backgroundColorGreen;
    // PM
    ws.getCell('Z10').value = 'PM';
    ws.getCell('Z10').font = { name: fontName, size: fontSize9 };
    ws.getCell('Z10').alignment = wrapTextMid;
    ws.getCell('Z10').border = borderFull;
    ws.getCell('Z10').fill = backgroundColorGreen;
    // UT
    ws.getCell('AA10').value = 'UT';
    ws.getCell('AA10').font = { name: fontName, size: fontSize9 };
    ws.getCell('AA10').alignment = wrapTextMid;
    ws.getCell('AA10').border = borderFull;
    ws.getCell('AA10').fill = backgroundColorGreen;
    // PT
    ws.getCell('AB10').value = 'PT';
    ws.getCell('AB10').font = { name: fontName, size: fontSize9 };
    ws.getCell('AB10').alignment = wrapTextMid;
    ws.getCell('AB10').border = borderFull;
    ws.getCell('AB10').fill = backgroundColorGreen;
    // OP
    ws.getCell('AC10').value = 'OP';
    ws.getCell('AC10').font = { name: fontName, size: fontSize9 };
    ws.getCell('AC10').alignment = wrapTextMid;
    ws.getCell('AC10').border = borderFull;
    ws.getCell('AC10').fill = backgroundColorGreen;
    // 役割
    ws.getCell('AD10').value = '役\r\n割';
    ws.getCell('AD10').font = { name: fontName, size: fontSize9 };
    ws.getCell('AD10').alignment = wrapTextMid;
    ws.getCell('AD10').border = borderFull;
    ws.getCell('AD10').fill = backgroundColorGreen;
    // OS
    ws.mergeCells('AE10:AG10');
    ws.getCell('AE10').value = 'OS';
    ws.getCell('AE10').font = { name: fontName, size: fontSize9 };
    ws.getCell('AE10').alignment = wrapTextMid;
    ws.getCell('AE10').border = borderFull;
    ws.getCell('AE10').fill = backgroundColorGreen;
    // 言語
    ws.mergeCells('AH10:AJ10');
    ws.getCell('AH10').value = '言語';
    ws.getCell('AH10').font = { name: fontName, size: fontSize9 };
    ws.getCell('AH10').alignment = wrapTextMid;
    ws.getCell('AH10').border = borderFull;
    ws.getCell('AH10').fill = backgroundColorGreen;
    // DB
    ws.mergeCells('AK10:AM10');
    ws.getCell('AK10').value = 'DB';
    ws.getCell('AK10').font = { name: fontName, size: fontSize9 };
    ws.getCell('AK10').alignment = wrapTextMid;
    ws.getCell('AK10').border = borderFull;
    ws.getCell('AK10').fill = backgroundColorGreen;
    // 開発環境ﾂｰﾙ／CPU
    ws.mergeCells('AN10:AP10');
    ws.getCell('AN10').value = '開発環境\r\nﾂｰﾙ／CPU';
    ws.getCell('AN10').font = { name: fontName, size: fontSize9 };
    ws.getCell('AN10').alignment = wrapTextMid;
    ws.getCell('AN10').border = borderFullThin;
    ws.getCell('AN10').fill = backgroundColorGreen;
    // TO-DO: foreach -> data ○
    let lastRowIndex = 11;
    if (data.listHistory?.length < 1) {
      ws.getRow(lastRowIndex).font = { name: fontName, size: fontSize9 };
      ws.getRow(lastRowIndex).height = 60;
      // STT
      ws.mergeCells(`B${lastRowIndex}:C${lastRowIndex}`);
      ws.getCell(`B${lastRowIndex}`).value = 1;
      ws.getCell(`B${lastRowIndex}`).numFmt = '00';
      ws.getCell(`B${lastRowIndex}`).alignment = alignMidCenter;
      ws.getCell(`B${lastRowIndex}`).border = borderFullThin;
      // description
      ws.mergeCells(`D${lastRowIndex}:N${lastRowIndex}`);
      ws.getCell(`D${lastRowIndex}`).value = '';
      ws.getCell(`D${lastRowIndex}`).alignment = alignTop;
      ws.getCell(`D${lastRowIndex}`).border = borderFullThin;
      // start date
      ws.mergeCells(`O${lastRowIndex}:P${lastRowIndex}`);
      ws.getCell(`O${lastRowIndex}`).value = '';
      ws.getCell(`O${lastRowIndex}`).numFmt = 'yy/mm';
      ws.getCell(`O${lastRowIndex}`).alignment = wrapTextTopCenter;
      ws.getCell(`O${lastRowIndex}`).border = borderFullThin;
      // end date
      ws.mergeCells(`Q${lastRowIndex}:R${lastRowIndex}`);
      ws.getCell(`Q${lastRowIndex}`).value = '';
      ws.getCell(`Q${lastRowIndex}`).numFmt = 'yy/mm';
      ws.getCell(`Q${lastRowIndex}`).alignment = wrapTextTopCenter;
      ws.getCell(`Q${lastRowIndex}`).border = borderFullThin;
      // months =IF(O11="","",IF(Q11="","",IF(Q11="継続中",DATEDIF(O11,TODAY(),"m"),DATEDIF(O11,Q11,"m")+1)))
      ws.mergeCells(`S${lastRowIndex}:U${lastRowIndex}`);
      // tslint:disable-next-line: max-line-length
      ws.getCell(`S${lastRowIndex}`).value = { formula: `=IF(O${lastRowIndex}="","",IF(Q${lastRowIndex}="","",IF(Q${lastRowIndex}="継続中",DATEDIF(O${lastRowIndex},TODAY(),"m"),DATEDIF(O${lastRowIndex},Q${lastRowIndex},"m")+1)))` };
      ws.getCell(`S${lastRowIndex}`).numFmt = '#ヶ月';
      ws.getCell(`S${lastRowIndex}`).alignment = alignTopCenter;
      ws.getCell(`S${lastRowIndex}`).border = borderFullThin;
      // 形態
      ws.getCell(`V${lastRowIndex}`).value = '';
      ws.getCell(`V${lastRowIndex}`).alignment = alignTopCenter;
      ws.getCell(`V${lastRowIndex}`).border = borderFullThin;
      // BI
      ws.getCell(`W${lastRowIndex}`).value = '';
      ws.getCell(`W${lastRowIndex}`).alignment = alignTopCenter;
      ws.getCell(`W${lastRowIndex}`).border = borderFullThin;
      // BD
      ws.getCell(`X${lastRowIndex}`).value = '';
      ws.getCell(`X${lastRowIndex}`).alignment = alignTopCenter;
      ws.getCell(`X${lastRowIndex}`).border = borderFullThin;
      // DD
      ws.getCell(`Y${lastRowIndex}`).value = '';
      ws.getCell(`Y${lastRowIndex}`).alignment = alignTopCenter;
      ws.getCell(`Y${lastRowIndex}`).border = borderFullThin;
      // PM
      ws.getCell(`Z${lastRowIndex}`).value = '';
      ws.getCell(`Z${lastRowIndex}`).alignment = alignTopCenter;
      ws.getCell(`Z${lastRowIndex}`).border = borderFullThin;
      // UT
      ws.getCell(`AA${lastRowIndex}`).value = '';
      ws.getCell(`AA${lastRowIndex}`).alignment = alignTopCenter;
      ws.getCell(`AA${lastRowIndex}`).border = borderFullThin;
      // PT
      ws.getCell(`AB${lastRowIndex}`).value = '';
      ws.getCell(`AB${lastRowIndex}`).alignment = alignTopCenter;
      ws.getCell(`AB${lastRowIndex}`).border = borderFullThin;
      // OP
      ws.getCell(`AC${lastRowIndex}`).value = '';
      ws.getCell(`AC${lastRowIndex}`).alignment = alignTopCenter;
      ws.getCell(`AC${lastRowIndex}`).border = borderFullThin;
      // POSITION
      ws.getCell(`AD${lastRowIndex}`).value = '';
      ws.getCell(`AD${lastRowIndex}`).alignment = alignTopCenter;
      ws.getCell(`AD${lastRowIndex}`).border = borderFullThin;
      // OS
      ws.mergeCells(`AE${lastRowIndex}:AG${lastRowIndex}`);
      ws.getCell(`AE${lastRowIndex}`).value = '';
      ws.getCell(`AE${lastRowIndex}`).alignment = wrapTextTopLeft;
      ws.getCell(`AE${lastRowIndex}`).border = borderFullThin;
      // listSkillName
      ws.mergeCells(`AH${lastRowIndex}:AJ${lastRowIndex}`);
      ws.getCell(`AH${lastRowIndex}`).value = '';
      ws.getCell(`AH${lastRowIndex}`).alignment = wrapTextTopLeft;
      ws.getCell(`AH${lastRowIndex}`).border = borderFullThin;
      // DB
      ws.mergeCells(`AK${lastRowIndex}:AM${lastRowIndex}`);
      ws.getCell(`AK${lastRowIndex}`).value = '';
      ws.getCell(`AK${lastRowIndex}`).alignment = wrapTextTopLeft;
      ws.getCell(`AK${lastRowIndex}`).border = borderFullThin;
      // TOOLS
      ws.mergeCells(`AN${lastRowIndex}:AP${lastRowIndex}`);
      ws.getCell(`AN${lastRowIndex}`).value = '';
      ws.getCell(`AN${lastRowIndex}`).alignment = wrapTextTopLeft;
      ws.getCell(`AN${lastRowIndex}`).border = borderFullThin;
    } else {
      data.listHistory?.forEach((element, index) => {
        ws.getRow(lastRowIndex).font = { name: fontName, size: fontSize9 };
        ws.getRow(lastRowIndex).height = 60;
        // STT
        ws.mergeCells(`B${lastRowIndex}:C${lastRowIndex}`);
        ws.getCell(`B${lastRowIndex}`).value = index + 1;
        ws.getCell(`B${lastRowIndex}`).numFmt = '00';
        ws.getCell(`B${lastRowIndex}`).alignment = alignMidCenter;
        ws.getCell(`B${lastRowIndex}`).border = borderFullThin;
        // description
        ws.mergeCells(`D${lastRowIndex}:N${lastRowIndex}`);
        ws.getCell(`D${lastRowIndex}`).value = element.description;
        ws.getCell(`D${lastRowIndex}`).alignment = alignTop;
        ws.getCell(`D${lastRowIndex}`).border = borderFullThin;
        // start date
        ws.mergeCells(`O${lastRowIndex}:P${lastRowIndex}`);
        ws.getCell(`O${lastRowIndex}`).value = new Date(element.startDate);
        ws.getCell(`O${lastRowIndex}`).numFmt = 'yy/mm';
        ws.getCell(`O${lastRowIndex}`).alignment = wrapTextTopCenter;
        ws.getCell(`O${lastRowIndex}`).border = borderFullThin;
        // end date
        ws.mergeCells(`Q${lastRowIndex}:R${lastRowIndex}`);
        ws.getCell(`Q${lastRowIndex}`).value = new Date(element.endDate);
        ws.getCell(`Q${lastRowIndex}`).numFmt = 'yy/mm';
        ws.getCell(`Q${lastRowIndex}`).alignment = wrapTextTopCenter;
        ws.getCell(`Q${lastRowIndex}`).border = borderFullThin;
        // months =IF(O11="","",IF(Q11="","",IF(Q11="継続中",DATEDIF(O11,TODAY(),"m"),DATEDIF(O11,Q11,"m")+1)))
        ws.mergeCells(`S${lastRowIndex}:U${lastRowIndex}`);
        // tslint:disable-next-line: max-line-length
        ws.getCell(`S${lastRowIndex}`).value = { formula: `=IF(O${lastRowIndex}="","",IF(Q${lastRowIndex}="","",IF(Q${lastRowIndex}="継続中",DATEDIF(O${lastRowIndex},TODAY(),"m"),DATEDIF(O${lastRowIndex},Q${lastRowIndex},"m")+1)))` };
        ws.getCell(`S${lastRowIndex}`).numFmt = '#ヶ月';
        ws.getCell(`S${lastRowIndex}`).alignment = alignTopCenter;
        ws.getCell(`S${lastRowIndex}`).border = borderFullThin;
        // 形態
        ws.getCell(`V${lastRowIndex}`).value = '';
        ws.getCell(`V${lastRowIndex}`).alignment = alignTopCenter;
        ws.getCell(`V${lastRowIndex}`).border = borderFullThin;
        // BI
        ws.getCell(`W${lastRowIndex}`).value = element.listScopeWork.some(item => item?.scopeWorkName?.includes(BI)) === true ? '○' : '';
        ws.getCell(`W${lastRowIndex}`).alignment = alignTopCenter;
        ws.getCell(`W${lastRowIndex}`).border = borderFullThin;
        // BD
        ws.getCell(`X${lastRowIndex}`).value = element.listScopeWork.some(item => item?.scopeWorkName?.includes(BD)) === true ? '○' : '';
        ws.getCell(`X${lastRowIndex}`).alignment = alignTopCenter;
        ws.getCell(`X${lastRowIndex}`).border = borderFullThin;
        // DD
        ws.getCell(`Y${lastRowIndex}`).value = element.listScopeWork.some(item => item?.scopeWorkName?.includes(DD)) === true ? '○' : '';
        ws.getCell(`Y${lastRowIndex}`).alignment = alignTopCenter;
        ws.getCell(`Y${lastRowIndex}`).border = borderFullThin;
        // PM
        ws.getCell(`Z${lastRowIndex}`).value = element.listScopeWork.some(item => item?.scopeWorkName?.includes(PM)) === true ? '○' : '';
        ws.getCell(`Z${lastRowIndex}`).alignment = alignTopCenter;
        ws.getCell(`Z${lastRowIndex}`).border = borderFullThin;
        // UT
        ws.getCell(`AA${lastRowIndex}`).value = element.listScopeWork.some(item => item?.scopeWorkName?.includes(UT)) === true ? '○' : '';
        ws.getCell(`AA${lastRowIndex}`).alignment = alignTopCenter;
        ws.getCell(`AA${lastRowIndex}`).border = borderFullThin;
        // PT
        ws.getCell(`AB${lastRowIndex}`).value = element.listScopeWork.some(item => item?.scopeWorkName?.includes(PT)) === true ? '○' : '';
        ws.getCell(`AB${lastRowIndex}`).alignment = alignTopCenter;
        ws.getCell(`AB${lastRowIndex}`).border = borderFullThin;
        // OP
        ws.getCell(`AC${lastRowIndex}`).value = element.listScopeWork.some(item => item?.scopeWorkName?.includes(OP)) === true ? '○' : '';
        ws.getCell(`AC${lastRowIndex}`).alignment = alignTopCenter;
        ws.getCell(`AC${lastRowIndex}`).border = borderFullThin;
        // POSITION
        ws.getCell(`AD${lastRowIndex}`).value = element.employeePositionProjectCode;
        ws.getCell(`AD${lastRowIndex}`).alignment = alignTopCenter;
        ws.getCell(`AD${lastRowIndex}`).border = borderFullThin;
        // OS
        ws.mergeCells(`AE${lastRowIndex}:AG${lastRowIndex}`);
        ws.getCell(`AE${lastRowIndex}`).value = element.osName;
        ws.getCell(`AE${lastRowIndex}`).alignment = wrapTextTopLeft;
        ws.getCell(`AE${lastRowIndex}`).border = borderFullThin;
        // listSkillName
        ws.mergeCells(`AH${lastRowIndex}:AJ${lastRowIndex}`);
        ws.getCell(`AH${lastRowIndex}`).value = element.listSkillName;
        ws.getCell(`AH${lastRowIndex}`).alignment = wrapTextTopLeft;
        ws.getCell(`AH${lastRowIndex}`).border = borderFullThin;
        // DB
        ws.mergeCells(`AK${lastRowIndex}:AM${lastRowIndex}`);
        ws.getCell(`AK${lastRowIndex}`).value = element.databaseName;
        ws.getCell(`AK${lastRowIndex}`).alignment = wrapTextTopLeft;
        ws.getCell(`AK${lastRowIndex}`).border = borderFullThin;
        // TOOLS
        ws.mergeCells(`AN${lastRowIndex}:AP${lastRowIndex}`);
        ws.getCell(`AN${lastRowIndex}`).value = element.tools;
        ws.getCell(`AN${lastRowIndex}`).alignment = wrapTextTopLeft;
        ws.getCell(`AN${lastRowIndex}`).border = borderFullThin;
        lastRowIndex++;
      });
    }
    return ws;
  }

  createEmployeeHistoryExcelFiles(title: string, listData: any[]) {
    listData.forEach(element => {
      const workbook = new ExcelJS.Workbook();
      let ws = workbook.addWorksheet('技術経歴書', { properties: { defaultColWidth: 2.25 } });
      ws = this.getEmployeeForm(ws, element);
      workbook.xlsx.writeBuffer().then((data: any) => {
        const blob = new Blob([data], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });
        const fileName = title + '_' + element.employeeFileName + '.xlsx';
        fs.saveAs(blob, fileName);
      });
    });
  }

  createEmployeeProfileExcelFile(title: string, listHeaders: string[], listData: any[]) {
    const workbook = new ExcelJS.Workbook();
    const worksheet = workbook.addWorksheet('Data');
    // add header
    const headerRow = worksheet.addRow(listHeaders);
    headerRow.eachCell(cell => {
      cell.alignment = alignMidCenter;
    });
    headerRow.font = { bold: true };
    for (let index = 0; index < listHeaders.length; index++) {
      if (index === 0) {
        worksheet.getColumn(index + 1).width = listHeaders[index].length + 4;
      } else {
        worksheet.getColumn(index + 1).width = listHeaders[index].length + 12;
      }
    }
    listData.forEach((employee, index) => {
      const row = [...employee];
      worksheet.addRow(row);
    });
    workbook.xlsx.writeBuffer().then((data) => {
      const blob = new Blob([data], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });
      const dateNow = this.datePipe.transform(new Date(), 'yyyyMMddhh:mm:ss').split(':').join('');
      const fileName = title + '_' + dateNow + '.xlsx';
      fs.saveAs(blob, fileName);
    });
  }
  // =========================
  generateExcel(title: string, data, header: string[], footer: string[]) {
    // get last column
    const lastIndex = header.length - 1;
    const lastCell = alphaBet.charAt(lastIndex);
    // create workbook and worksheet
    const workbook = new ExcelJS.Workbook();
    const worksheet = workbook.addWorksheet('Data');
    const titleRow = worksheet.addRow([title.toUpperCase()]);
    titleRow.font = { name: 'Tahoma', family: 4, size: 16, bold: true };
    worksheet.addRow([]);
    // date now
    worksheet.addRow(['Date : ' + this.datePipe.transform(new Date(), 'medium')]);
    // add Image
    const logo = workbook.addImage({
      base64: logoBase64,
      extension: 'png',
    });
    worksheet.addImage(logo,
      `${lastCell}1:${lastCell}3`,
      { ext: { width: 224, height: 224 } }
    );
    worksheet.getCell('A1').alignment = { vertical: 'middle', horizontal: 'middle' };
    // add blank row
    worksheet.addRow([]);
    // add Header Row
    const headerRow = worksheet.addRow(header);
    // fomrat header
    headerRow.eachCell((cell, index) => {
      cell.fill = {
        type: 'pattern',
        pattern: 'solid',
        fgColor: { argb: 'FFFFFF00' },
        bgColor: { argb: 'FF0000FF' }
      };
      cell.border = {
        top: { style: 'thin' }, left: { style: 'thin' },
        bottom: { style: 'thin' }, right: { style: 'thin' }
      };
      cell.alignment = { vertical: 'middle', horizontal: 'center' };
      worksheet.getColumn(index).width = header[index - 1].length + 5;
    });
    headerRow.font = { bold: true };
    // body
    data.forEach((d, index) => {
      const row = [...d];
      // add index column to each row
      row.unshift(index + 1);
      worksheet.addRow(row);
    });
    // add blank row
    worksheet.addRow([]);
    // footer Row
    const footerRow = worksheet.addRow(footer);
    footerRow.getCell(1).fill = {
      type: 'pattern',
      pattern: 'solid',
      fgColor: { argb: 'ffffff' }
    };
    footerRow.getCell(1).border = { top: { style: 'thin' }, left: { style: 'thin' }, bottom: { style: 'thin' }, right: { style: 'thin' } };
    footerRow.getCell(1).alignment = alignMidRight;
    // Merge Cells
    worksheet.mergeCells(`A${footerRow.number}:${lastCell}${footerRow.number}`);
    // Generate Excel File with given name
    // tslint:disable-next-line:no-shadowed-variable
    workbook.xlsx.writeBuffer().then((data) => {
      const blob = new Blob([data], { type: 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet' });
      const dateNow = this.datePipe.transform(new Date(), 'yyyyMMddhh:mm:ss').split(':').join('');
      const fileName = 'List' + '_' + title + '_' + dateNow + '.xlsx';
      fs.saveAs(blob, fileName);
    });
  }
}

