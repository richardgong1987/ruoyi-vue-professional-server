const fs = require('fs-extra')
const menuTomlPath = "./menu/menu.json";

async function exportFile(f, text) {
  await fs.outputFile(f, text);
}

async function outMenuToml(text) {
  await exportFile(menuTomlPath, text)
}

async function outSql(text) {
  await exportFile("../sql/menu.sql", text)
}

async function reorderMenuIds() {
  const menu = require('./menu/menu.json');
  let id = 3000;

  menu.forEach(v1=>{
    v1.menuId = id++;
    v1.children.forEach(v2 => {
      v2.menuId = id++;
      v2.children.forEach(v3 => {
        v3.menuId = id++;
      })
    })
  })
  await outMenuToml(JSON.stringify(menu));
}

async function translatorJpToEn() {
  const menu = require('./menu/menu.json');
  let id = 3000;

  menu.forEach(v1=>{
    v1.menuId = id++;
    v1.children.forEach(v2 => {
      v2.menuId = id++;
      v2.children.forEach(v3 => {
        v3.menuId = id++;
      })
    })
  })
  await outMenuToml(JSON.stringify(menu));
}

exports.reorderMenuIds = reorderMenuIds;
exports.translatorJpToEn = translatorJpToEn;
exports.exportFile = exportFile;
exports.outSql = outSql;
exports.outMenuToml = outMenuToml;
