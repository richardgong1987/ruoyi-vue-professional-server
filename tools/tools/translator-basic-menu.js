const {translate} = require('bing-translate-api');
const fs = require('fs-extra')


async function trans(text) {
  let res = await translate(text, "zh-Hans", 'ja', true);
  return res.translation;
}

async function translatorZhToJa() {
  const text = require('fs').readFileSync('../sql/basic-menu.sql', 'utf8');
  var obj = {};
  text.replace(/[\u4e00-\u9fa5]+/g, function (text) {
    obj[text] = text;
    return text;
  });

  for (const k in obj) {
    obj[k] = await trans(obj[k]);
  }

  await fs.outputFile("../sql/basic-menu-tmp.sql", text.replace(/[\u4e00-\u9fa5]+/g, function (text) {
    return obj[text];
  }));
}

translatorZhToJa();
