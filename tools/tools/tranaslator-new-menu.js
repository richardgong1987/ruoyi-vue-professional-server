const {translate} = require('bing-translate-api');
const fs = require('fs-extra')
const menu = require("./menu/menu.json");

const formatToHump = (value) => {
  return value.replace(/\_(\w)/g, (_, letter) => letter.toUpperCase())
}
async function trans(v,lang="ja") {
  let res = await translate(v.name, lang, 'en', true);
  v.path = res.translation.toLowerCase().replace(/[\s-]+/g, '_').replace(/_+/g, '_').replace("测试中", "test");
}

async function translatorJpToEn() {
  const menu = require('./menu/menu.json');
  for (let v1 of menu) {
    await trans(v1);
    for (let v2 of v1.children) {
      await trans(v2);
      for (let v3 of v2.children) {
        await trans(v3);
      }
    }
  }
  await fs.outputFile("./menu/tmp.json", JSON.stringify(menu));
}

translatorJpToEn();
