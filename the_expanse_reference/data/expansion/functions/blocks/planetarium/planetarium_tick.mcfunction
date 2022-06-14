execute unless block ~ ~ ~ minecraft:spawner run function expansion:blocks/planetarium/destroy
execute if entity @p[distance=..50] as @e[type=armor_stand,tag=planetarium_part] run function expansion:blocks/planetarium/spin
