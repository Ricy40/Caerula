effect give @p levitation 1 255 true
scoreboard players set @p exp_warmup 2
fill ~-11 ~-1 ~-11 ~11 ~21 ~11 minecraft:barrier hollow
execute if block ~ ~-1 ~ minecraft:barrier run tag @p remove generate_room
execute if block ~ ~-1 ~ minecraft:barrier run scoreboard players set @p exp_warmup 0