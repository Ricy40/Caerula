execute store result score @s exp_distance run locate structure #expansion:jupiter_fleet
tellraw @s ["",{"text":"The nearest Jupiter Fleet is ", "color":"aqua"},{"score":{"name":"@s","objective":"exp_distance"}, "bold":true, "color":"aqua"},{"text":" blocks away", "color":"aqua"}]
