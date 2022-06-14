function expansion:handy_tools/gravity/low_gravity/low_gravity_1
execute positioned ~ ~1 ~ as @e[type=armor_stand,distance=..2,tag=nuke_trigger,limit=1,sort=nearest] at @s run function expansion:events/mars/detonate_nuke
