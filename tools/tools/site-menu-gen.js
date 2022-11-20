const {reorderMenuIds, outSql} = require("./utils");
var sql = `
USE \`ry-vue\`;

DELETE
FROM sys_menu
WHERE menu_id >= 3000;

DELETE
FROM sys_menu
WHERE menu_id=4;

REPLACE into sys_menu values('1', '系统管理', '0', '1000', 'system',           null, '', 1, 0, 'M', '0', '0', '', 'system',   'admin', sysdate(), '', null, '系统管理目录');
REPLACE into sys_menu values('2', '系统监控', '0', '1001', 'monitor',          null, '', 1, 0, 'M', '0', '0', '', 'monitor',  'admin', sysdate(), '', null, '系统监控目录');
REPLACE into sys_menu values('3', '系统工具', '0', '1002', 'tool',             null, '', 1, 0, 'M', '0', '0', '', 'tool',     'admin', sysdate(), '', null, '系统工具目录');

`;

(async function () {
  await reorderMenuIds()
  let menuData = require('./menu/menu.json');
  genSql(menuData);
  await outSql(sql);
}());

function genSql(menu) {
  menu.forEach((v1, key1) => {
    sql += firstMenu(v1, key1);
    v1.children.forEach((v2, key2) => {
      sql += secondaryMenu(v2, key2, v1);
      sql += buttonMenu(v2, v1);
    })
  })

}


/**
 *  -- 一级菜单
 */

function firstMenu(v1, order_num, parentId = '0') {
  return `REPLACE INTO sys_menu VALUES ('${v1.menuId}', '${v1.name}', '${parentId}', '${order_num}', '${v1.path}', NULL, '', 1, 0, 'M', '0', '0',        '', '${v1.icon}', 'admin', sysdate(), '',NULL, '${v1.name}-list'); \n`;
}

/**
 * -- 二级菜单
 */
function secondaryMenu(v2, order_num, v1) {
  return `REPLACE INTO sys_menu VALUES ('${v2.menuId}', '${v2.name}', '${v1.menuId}', '${order_num}', '${v2.path}', '${v1.path}/${v2.path}/index','', 1, 0, 'C', '0', '0','${v1.path}:${v2.path}:list', '${v2.icon}', 'admin', sysdate(), '', NULL, '${v2.name}-level3');\n`;
}



/**
 * -- 管理按钮
 */
function buttonMenu(v2, v1) {
  return v2.children.map((value, index) => {
    return `REPLACE INTO sys_menu VALUES ('${value.menuId}', '${v2.name}${value.path}', '${v2.menuId}', '${index}', '', '', '', 1, 0, 'F', '0', '0','${v1.path}:${v2.path}:${value.path}', '#', 'admin',sysdate(), '', NULL, '');\n`;
  }).join("");
}
