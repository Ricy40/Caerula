execute if score #temp cw_hp_old matches ..0 run kill @s
execute if score #temp cw_hp_old matches 1..511 run function expansion:handy_tools/custom_damage/set_hp/l3_i0
execute if score #temp cw_hp_old matches 512..1023 run function expansion:handy_tools/custom_damage/set_hp/l3_i512