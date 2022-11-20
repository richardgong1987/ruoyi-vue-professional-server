
USE `ry-vue`;

DELETE
FROM sys_menu
WHERE menu_id >= 3000;

DELETE
FROM sys_menu
WHERE menu_id=4;

REPLACE into sys_menu values('1', '系统管理', '0', '1000', 'system',           null, '', 1, 0, 'M', '0', '0', '', 'system',   'admin', sysdate(), '', null, '系统管理目录');
REPLACE into sys_menu values('2', '系统监控', '0', '1001', 'monitor',          null, '', 1, 0, 'M', '0', '0', '', 'monitor',  'admin', sysdate(), '', null, '系统监控目录');
REPLACE into sys_menu values('3', '系统工具', '0', '1002', 'tool',             null, '', 1, 0, 'M', '0', '0', '', 'tool',     'admin', sysdate(), '', null, '系统工具目录');

