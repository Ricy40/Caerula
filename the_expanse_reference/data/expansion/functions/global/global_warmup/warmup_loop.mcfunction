scoreboard players remove @s exp_warmup 1
execute if entity @s[tag=generate_room] in expansion:storage run function expansion:items/transporter/generate_room
execute if score @s exp_warmup matches 0 run function expansion:global/global_warmup/warmup_end
